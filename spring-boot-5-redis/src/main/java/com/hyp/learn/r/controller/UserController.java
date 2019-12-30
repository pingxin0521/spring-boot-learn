package com.hyp.learn.r.controller;

import com.hyp.learn.r.model.User;
import com.hyp.learn.r.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.r.controller
 * hyp create at 19-12-24
 **/
@Slf4j
@RequestMapping("/redis")
@RestController
public class UserController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("set")
    public boolean redisset(String key, String value){
        User userEntity =new User();
        userEntity.setId(1);
        userEntity.setName("平心");
        userEntity.setAge(20);

        //return redisUtil.set(key,userEntity,ExpireTime);

        return redisUtil.set(key,value);
    }

    @RequestMapping("get")
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }

    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
        User user = new User();
        user.setName("平心");
        user.setPass("123456");
        user.setAge(26);
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

//    登录 Redis 输入 keys '*sessions*'
    //获取session
    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }

    //@Cacheable
    //
    //@Cacheable("product")
    //@Cacheable(value = {"product","order"}, key = "#root.targetClass+'-'+#id")
    //@Cacheable(value = "product", key = "#root.targetClass+'-'+#id")
    //自定义cacheManager
    //@Cacheable(value = "product", key = "#root.targetClass+'-'+#id” cacheManager="cacheManager")
    //@CachePut
    //应用到写数据的方法上，如新增/修改方法
    //@CachePut(value = "product", key = "#root.targetClass+'-'+#product.id")
    //@CacheEvict
    //即应用到移除数据的方法上，如删除方法
    //@CacheEvict(value = "product", key = "#root.targetClass+'-'+#id")
    //提供的SpEL上下文数据

    //https://segmentfault.com/a/1190000015632570

    //http://www.apgblogs.com/springboot-redis/

}
