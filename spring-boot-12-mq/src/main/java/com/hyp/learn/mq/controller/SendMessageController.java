package com.hyp.learn.mq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 然后写个简单的接口进行消息推送（根据需求也可以改为定时任务等等，具体看需求
 *     * 队列名---》路由键值
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mq.controller
 * hyp create at 20-1-3
 **/
@RestController
public class SendMessageController {
    @Qualifier("myRabbitTemplate")
    @Autowired
    RabbitTemplate rabbitTemplate;
    //使用RabbitTemplate,这提供了接收/发送等等方法

    /**
     * direct交换机exchange.direct
     * 绑定队列:pingxin--->pingxin
     * @return
     */
    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：pingxin 发送到交换机exchange.direct
        rabbitTemplate.convertAndSend("exchange.direct", "pingxin", map);
        return "ok";
    }

    /**
     * topic交换机topicExchange
     * 绑定队列：topic.man-->topic.man,topic.woman-->topic.#
     * @return
     */
    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", manMap);
        return "ok";
    }

    /**
     * topic交换机topicExchange
     * 绑定队列：topic.man-->topic.man,topic.woman-->topic.#
     * @return
     */
    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", womanMap);
        return "ok";
    }

    /**
     * fanout类型交换机fanoutExchange
     * 绑定队列fanout.A,fanout.B,fanout.C
     * @return
     */
    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: testFanoutMessage ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "ok";
    }

    /**
     * 1.消息推送到server，但是在server里找不到交换机
     * 把消息推送到名为‘non-existent-exchange’的交换机上（这个交换机是没有创建没有配置的）
     * 触发的是 ConfirmCallback 回调函数。
     * @return
     */
    @GetMapping("/TestMessageAck")
    public String TestMessageAck() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
        return "ok";
    }

    /**
     * 2.消息推送到server，找到交换机了，但是没找到队列
     * 这种情况就是需要新增一个交换机，但是不给这个交换机绑定队列，我来简单地在DirectRabitConfig里面新增一个直连交换机，名叫‘lonelyDirectExchange’，但没给它做任何绑定配置操作
     *
     * 把消息推送到名为‘lonelyDirectExchange’的交换机上（这个交换机是没有任何队列配置的）
     *
     * 这种情况触发的是 ConfirmCallback和RetrunCallback两个回调函数。
     * @return
     */
    @GetMapping("/TestMessageAck2")
    public String TestMessageAck2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    /**
     * 3. 消息推送到sever，交换机和队列啥都没找到
     *
     * 这种情况触发的是 ConfirmCallback 回调函数。
     * 4. 消息推送成功
     * 按照正常调用之前消息推送的接口就行，就调用下 /sendFanoutMessage接口
     * 这种情况触发的是 ConfirmCallback 回调函数。
     */



}
