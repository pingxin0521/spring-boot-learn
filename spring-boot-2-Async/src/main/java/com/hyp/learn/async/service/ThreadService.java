package com.hyp.learn.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.async.service
 * hyp create at 19-12-21
 **/

@Service
public class ThreadService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Qualifier("sendMailExecutorService")
    @Autowired
    private ExecutorService sendMailExecutorService;

    @Qualifier("heartbeatExecutorService")
    @Autowired
    private ExecutorService heartbeatExecutorService;


    public void heartbeatService() {
        heartbeatExecutorService.submit(() -> {

            // TODO 负责心跳有关的工作
            logger.info("Execute heartbeatService asynchronously, thread name = {}", Thread.currentThread().getName());

        });
    }

    public Future<Boolean> sendMailService() {
        return sendMailExecutorService.submit(() -> {

            logger.info("Execute sendMailService asynchronously, thread name = {}", Thread.currentThread().getName());

            // 休息1秒，模拟邮件发送过程
            TimeUnit.SECONDS.sleep(1);
            return true;
        });
    }
}
