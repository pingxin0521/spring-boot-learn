package com.hyp.learn.async.controller;

import com.hyp.learn.async.service.ArithmeticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/api/async", produces = "application/json;charset=utf-8")
public class AsyncController implements Constants {

    private final static Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private ArithmeticService arithmeticService;

    @RequestMapping(value = {"/asyncData"}, method = RequestMethod.GET)
    public void asyncData() {
        long start = System.currentTimeMillis();
        long sync = 0L;
        try {
            logger.info("--------------------------------------------\n");
            logger.info("每个任务执行的时间是：" + ArithmeticService.DoTime + "（毫秒）");

            arithmeticService.subByVoid();

            logger.info("--------------------------------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        logger.info("\t........请求响应时间为：" + (end - start) + "（毫秒）");

    }

    @RequestMapping(value = {"/asyncGetData"}, method = RequestMethod.GET)
    public Long asyncBackData() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Future<Long> task = null;
        try {
            logger.info("--------------------------------------------\n");
            logger.info("每个任务执行的时间是：" + ArithmeticService.DoTime + "（毫秒）");

            task = arithmeticService.subByAsync();

            logger.info("--------------------------------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        logger.info("\t........请求响应时间为：" + (end - start) + "（毫秒）");
        return task.get();
    }

    @RequestMapping(value = {"/syncData"}, method = RequestMethod.GET)
    public void syncData() {
        long start = System.currentTimeMillis();
        long sync = 0L;
        try {
            logger.info("--------------------------------------------\n");
            logger.info("每个任务执行的时间是：" + ArithmeticService.DoTime + "（毫秒）");
            sync = arithmeticService.subBySync();
            logger.info("同步任务执行的时间是：" + sync + "（毫秒）");

            logger.info("--------------------------------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        logger.info("\t........请求响应时间为：" + (end - start) + "（毫秒）");
    }

    @RequestMapping(value = {"/syncException"}, method = RequestMethod.GET)
    public void syncException() {
        long start = System.currentTimeMillis();
        long sync = 0L;
        try {
            logger.info("--------------------------------------------\n");
            logger.info("每个任务执行的时间是：" + ArithmeticService.DoTime + "（毫秒）");
            arithmeticService.asyncExection();
            logger.info("同步任务执行的时间是：" + sync + "（毫秒）");

            logger.info("--------------------------------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        logger.info("\t........请求响应时间为：" + (end - start) + "（毫秒）");
    }

    /**
     * 自定义实现线程异步
     */
    @RequestMapping(value = {"/mine", "/m*"}, method = RequestMethod.GET)
    public void mineAsync() {
        for (int i = 0; i < 100; i++) {
            try {
                arithmeticService.doMineAsync(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
