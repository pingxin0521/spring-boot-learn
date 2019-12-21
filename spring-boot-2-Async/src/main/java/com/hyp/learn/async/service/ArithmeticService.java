package com.hyp.learn.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 操作计算 service 类：简单实现有关异步和同步两种计算方式的性能比较
 */
@Component
public class ArithmeticService {

    private final static Logger logger = LoggerFactory.getLogger(ArithmeticService.class);

    public static final int DoTime = 5000;

    /**
     * 异步任务 只需要在所需实现异步的方法上加上@Async注解， 并通过Future<T>来接受异步方法的处理结果
     * 通过@Async注解表明该方法是个异步方法，如果注解在类级别，则表明该类所有的方法都是异步方法
     * 其中 org.springframework.scheduling.annotation.AsyncResult 实现了 java.util.concurrent.Future 接口，并增加了一些额外有用的功能。
     *
     * @return
     */
    @Async
    public Future<Long> subByAsync() throws Exception {
        logger.info("Execute method asynchronously, thread name = {}", Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        long sum = 0;
        Thread.sleep(DoTime);
        long end = System.currentTimeMillis();
        sum = end - start;
        logger.info("\t 完成任务一");

        return new AsyncResult<>(sum);
    }

    /**
     * 仅使用异步注解的方式实现异步方法
     *
     * @return
     */
    @Async
    public void subByVoid() throws Exception {
        long start = System.currentTimeMillis();
        long sum = 0;
        Thread.sleep(DoTime);
        long end = System.currentTimeMillis();
        sum = end - start;
        logger.info("\t 完成任务二   ");
        logger.info("注解任务执行的时间是： " + sum + "（毫秒）");
        logger.info("Execute method asynchronously, thread name = {}", Thread.currentThread().getName());
    }

    /**
     * 使用同步计算的方式--同步调用
     *
     * @return
     */
    public long subBySync() throws Exception {
        long start = System.currentTimeMillis();
        long sum = 0;
        Thread.sleep(DoTime);
        long end = System.currentTimeMillis();
        sum = end - start;
        logger.info("\t 完成任务三   ");
        return sum;
    }

    @Async("selfConfigThreadPool")
    public void doMineAsync(int i) throws Exception {
        System.out.println("------\t:" + i);
        Thread.sleep(10000);
    }

    @Async
    public void asyncExection() {
        logger.info("Execute method asynchronously, thread name = {}", Thread.currentThread().getName());
        throw new RuntimeException("异常");
    }
}
