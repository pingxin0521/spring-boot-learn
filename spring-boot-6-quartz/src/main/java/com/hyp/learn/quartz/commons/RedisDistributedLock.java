package com.hyp.learn.quartz.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 实现redis分布式锁
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.commons
 * hyp create at 19-12-31
 **/


@Component
public class RedisDistributedLock {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private DefaultRedisScript<Long> redisScript;


    private Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);

    //redis分布式锁的默认超时时间
    private long timeout = 3000;

    //redis分布式锁的的value
    private static final Long RELEASE_SUCCESS = 1L;

    private static final String LOCK_SUCCESS = "OK";

    /**
     * 获取锁
     *
     * @param key lockKey
     * @return true获取到锁，false未获取到锁
     */
    public boolean lock(String key) {
        return lock(key, LOCK_SUCCESS);
    }

    public boolean lock(String key, String value) {
        long start = System.currentTimeMillis();
        while (true) {
            //检测是否超时
            if (System.currentTimeMillis() - start > timeout) {
                return false;
            }
            //执行set命令
            Boolean absent = redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.MILLISECONDS);//1
            //其实没必要判NULL，这里是为了程序的严谨而加的逻辑
            if (absent == null) {
                return false;
            }
            //是否成功获取锁
            if (absent) {
                return true;
            }
        }
    }
    public boolean unLock(String key){
       return unLock(key,LOCK_SUCCESS);
    }

    public boolean unLock(String key, String value) {
        //使用Lua脚本：先判断是否是自己设置的锁，再执行删除
        Long result = redisTemplate.execute(redisScript, Arrays.asList(key, value));
        //返回最终结果
        return RELEASE_SUCCESS.equals(result);
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }




}
