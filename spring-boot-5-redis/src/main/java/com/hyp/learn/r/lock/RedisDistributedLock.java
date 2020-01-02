package com.hyp.learn.r.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
    private RedisTemplate<String, Object> redisTemplate;

    private Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);

    //redis分布式锁的默认超时时间
    private static final Long DEFAULT_LOCK_TIMEOUT = 60 * 1000L;

    //redis分布式锁的的value
    private static final Integer LOCK_KEY_VALUE = 1;

    /**
     * 获取锁
     *
     * @param lockKey lockKey
     * @return true获取到锁，false未获取到锁
     */
    public boolean lock(String lockKey) {
        return lock(lockKey, DEFAULT_LOCK_TIMEOUT);
    }

    /**
     * 获取锁
     *
     * @param lockKey lockKey
     * @param timeout 锁超时时间，单位ms
     * @return
     */
    public boolean lock(String lockKey, long timeout) {
        logger.info("==============》开始执行加锁操作");
        if (StringUtils.isEmpty(lockKey)) {
            logger.error("传递字符串为空，不能进行加锁");
            return false;
        }
        Boolean lockResult = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();
            byte[] value = jdkSerializer.serialize(LOCK_KEY_VALUE);
            return connection.setNX(lockKey.getBytes(), value);
        });
        if (lockResult) {
            logger.info("获取锁成功，设置锁超时时间");
            redisTemplate.expire(lockKey, timeout, TimeUnit.MILLISECONDS);
        }
        return lockResult;
    }


    /**
     * 进行解锁操作
     *
     * @param lockKey lockKey
     */
    public void unLock(String lockKey) {
        logger.info("==============》开始执行解锁操作");
        if (StringUtils.isEmpty(lockKey)) {
            logger.error("传递字符串为空，不能进行解锁");
            return;
        }
        Integer redisLockKeyValue = (Integer) redisTemplate.opsForValue().get(lockKey);
        if (redisLockKeyValue != null && redisLockKeyValue > 0) {
            redisTemplate.delete(lockKey);
        }
    }


}
