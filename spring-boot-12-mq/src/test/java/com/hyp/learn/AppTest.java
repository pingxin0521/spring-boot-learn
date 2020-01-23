package com.hyp.learn;

import com.hyp.learn.mq.RabbitmqApplication;
import com.hyp.learn.mq.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RabbitmqApplication.class})
public class AppTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange() {

//		amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//		System.out.println("创建完成");

//		amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        //创建绑定规则

//		amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));

        //amqpAdmin.de
    }

    //单播（点对点）
    @Test
    public void contextLoads() {
        //Message需要自己构造一个；定义消息体内容和消息头
        //rabbitTemplate.send(exchage,routeKey,message);

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
        //rabbitTemplate.convertAndSend(exchage,routKey,object);
        Map<String, String> map = new HashMap();
        map.put("a", "zs");
        map.put("b", "ls");
        //对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct", "hyp.news", new Book("西游记", "吴承恩").toString());
    }

    //接收数据
    @Test
    public void receive() {

        Object o = rabbitTemplate.receiveAndConvert("hyp.news");
//        class java.util.HashMap
//        {a=zs, b=ls}
        System.out.println(o.getClass());
        System.out.println(o.toString());
    }


    //广播
    @Test
    public void sendMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("mnmn", "kjkj").toString());
    }
}
