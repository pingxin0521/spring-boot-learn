package com.hyp.learn.mq.controller;

import com.hyp.learn.mq.service.ProdServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mq.controller
 * hyp create at 20-1-22
 **/
@RestController
@RequestMapping("/mq")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ProdServer prodServer;

    @RequestMapping(value = "/direct/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String direct(String eventIds) {
        String msg = UUID.randomUUID().toString();
        try {
            prodServer.convertAndSend(msg);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("-----------------mq添加出错------------");
        }
        return msg;
    }

    @RequestMapping(value = "/fanout/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String fanout(String eventIds) {
        String msg = UUID.randomUUID().toString();
        try {
            prodServer.fanoutSendMsg(msg);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("-----------------mq添加出错------------");
        }
        return msg;
    }

    @RequestMapping(value = "/topic/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String topic(String eventIds) {
        String msg = UUID.randomUUID().toString();
        try {
            prodServer.topicSendMsg1(msg);
            prodServer.topicSendMsg2(msg);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("-----------------mq添加出错------------");
        }
        return msg;
    }

    @RequestMapping(value = "/headers/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String headers(String eventIds) {
        String msg = UUID.randomUUID().toString();
        try {
            prodServer.headersSendMsg1(msg);
            prodServer.headersSendMsg2(msg);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("-----------------mq添加出错------------");
        }
        return msg;
    }

}
