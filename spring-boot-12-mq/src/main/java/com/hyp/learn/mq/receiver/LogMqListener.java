package com.hyp.learn.mq.receiver;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mq.receiver
 * hyp create at 20-1-22
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 监听用户log队列消息
 *
 * @author Administrator
 */
@Component
public class LogMqListener {

    private static final Logger log = LoggerFactory.getLogger(LogMqListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 监听消费用户日志
     *
     * @param message
     */
    @RabbitListener(queues = "${log.user.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeUserLogQueue(@Payload byte[] message) {
        try {
            String userLog = objectMapper.readValue(message, String.class);
            log.info("监听消费用户日志 监听到消息： {} ", userLog);
            //userLogMapper.insertSelective(userLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听消费用户日志 fanout模式
     *
     * @param message
     */
    @RabbitListener(queues = "${log.user.fanout.queue1}", containerFactory = "singleListenerContainer")
    public void fanoutUserLogQueue1(@Payload byte[] message) {
        try {
            String userLog = objectMapper.readValue(message, String.class);
            log.info("监听消费用户日志【队列1】 监听到消息： {} ", userLog);
            //userLogMapper.insertSelective(userLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听消费用户日志 fanout模式
     *
     * @param message
     */
    @RabbitListener(queues = "${log.user.fanout.queue2}", containerFactory = "singleListenerContainer")
    public void fanoutUserLogQueue2(@Payload byte[] message) {
        try {
            String userLog = objectMapper.readValue(message, String.class);
            log.info("监听消费用户日志 【队列2】监听到消息： {} ", userLog);
            //userLogMapper.insertSelective(userLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听消费用户日志 topic模式
     *
     * @param message
     */
    @RabbitListener(queues = "${log.user.topic.queue}", containerFactory = "singleListenerContainer")
    public void topicUserLogQueue(@Payload byte[] message) {
        try {
            String userLog = objectMapper.readValue(message, String.class);
            log.info("监听消费用户日志监听到消息： {} ", userLog);
            //userLogMapper.insertSelective(userLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听消费用户日志 headers模式
     *
     * @param message
     */
    @RabbitListener(queues = "${log.user.headers.queue}", containerFactory = "singleListenerContainer")
    public void headersUserLogQueue(@Payload byte[] message) {
        try {
            String userLog = objectMapper.readValue(message, String.class);
            log.info("监听消费用户日志监听到消息： {} ", userLog);
            //userLogMapper.insertSelective(userLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
