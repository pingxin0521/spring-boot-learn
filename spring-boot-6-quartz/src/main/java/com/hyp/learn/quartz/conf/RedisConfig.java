package com.hyp.learn.quartz.conf;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.conf
 * hyp create at 19-12-31
 **/
@Configuration
@EnableCaching
public class RedisConfig {


    /**
     *  定义 StringRedisTemplate ，指定序列化和反序列化的处理类
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //序列化 值时使用此序列化方法
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public DefaultRedisScript<Long> defaultRedisScript() {
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(Long.class);
        defaultRedisScript.setScriptText("if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end");
//        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("delete.lua")));
        return defaultRedisScript;
    }

//    @Bean
//    public CacheManager cacheManager(RedisTemplate<String,String> redisTemplate) {
//        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
//        //使用前缀
//        rcm.setUsePrefix(true);
//        //缓存分割符 默认为 ":"
////        rcm.setCachePrefix(new DefaultRedisCachePrefix(":"));
//        //设置缓存过期时间
//        //rcm.setDefaultExpiration(60);//秒
//        return rcm;
//    }

}
