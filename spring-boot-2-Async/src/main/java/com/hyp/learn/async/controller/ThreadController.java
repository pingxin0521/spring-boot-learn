package com.hyp.learn.async.controller;

import com.hyp.learn.async.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.async.controller
 * hyp create at 19-12-21
 **/
@RestController
@RequestMapping(value = "/api/asyncThread", produces = "application/json;charset=utf-8")
public class ThreadController implements Constants {

    @Autowired
    private ThreadService threadService;

    /**
     * 请求，立即返回，但是不是具体的执行结果，具体的任务在线程池中慢慢的执行
     */
    @GetMapping("/heartbeat")
    public String asyncData() {
        threadService.heartbeatService();
        return "执行完毕";
    }

    /**
     * 请求，执行完毕后再返回具体的结果，具体的任务在线程池中执行
     */
    @GetMapping("/sendMail")
    public Boolean asyncGetData() throws Exception {
        return threadService.sendMailService().get();
    }

}
