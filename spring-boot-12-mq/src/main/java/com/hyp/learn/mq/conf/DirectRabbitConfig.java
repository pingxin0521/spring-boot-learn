package com.hyp.learn.mq.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 接着我们先使用下direct exchange(直连型交换机),创建DirectRabbitConfig.java：
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mq.conf
 * hyp create at 20-1-3
 **/
@Configuration
public class DirectRabbitConfig {
    //队列 起名：pingxin
    @Bean
    public Queue TestDirectQueue() {
        return new Queue("pingxin",true);  //true 是否持久
    }

    //Direct交换机 起名：exchange.direct
    @Bean
    DirectExchange TestDirectExchange() {
        return new DirectExchange("exchange.direct");
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：pingxin
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("pingxin");
    }


    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }

}
