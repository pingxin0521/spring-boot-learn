package com.hyp.learn.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.async.config
 * hyp create at 19-12-21
 **/
@Configuration
public class ThreadConfig {

    /**
     * 邮件服务
     * @return
     */
    @Bean("sendMailExecutorService")
    public ExecutorService sendMailExecutorService() {
        return new ThreadPoolExecutor(2, 2,
                60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
    }

    /**
     * 心跳服务
     * @return
     */
    @Bean("heartbeatExecutorService")
    public ExecutorService heartbeatExecutorService() {
        return new ThreadPoolExecutor(1, 1,
                60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
    }
}
