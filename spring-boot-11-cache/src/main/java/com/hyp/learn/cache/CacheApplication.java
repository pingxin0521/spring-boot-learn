package com.hyp.learn.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.qs
 * hyp create at 19-12-21
 **/
@SpringBootApplication
@MapperScan("com.hyp.learn.cache.dao")
@EnableCaching
public class CacheApplication {


    public static void main(String[] args) {

        // devtools：是spring boot的一个热部署工具
        //设置 spring.devtools.restart.enabled 属性为false，可以关闭该特性.
        //System.setProperty("spring.devtools.restart.enabled","false");

        // 启动Sprign Boot
        ConfigurableApplicationContext ctx = SpringApplication.run(CacheApplication.class);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    //自定义KeyGenerator
    @Bean(name = "myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName()+"["+Arrays.asList(params).toString() +"]";
            }
        };
    }
}
