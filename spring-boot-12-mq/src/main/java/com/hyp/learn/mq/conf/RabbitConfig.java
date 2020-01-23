package com.hyp.learn.mq.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * 先从总体的情况分析，推送消息存在四种情况：
 * <p>
 * ①消息推送到server，但是在server里找不到交换机
 * ②消息推送到server，找到交换机了，但是没找到队列
 * ③消息推送到sever，交换机和队列啥都没找到
 * ④消息推送成功
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mq.conf
 * hyp create at 20-1-22
 **/
@Slf4j
@Configuration
public class RabbitConfig {

    @Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * 单一消费者
     *
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setTxSize(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    /**
     * 多个消费者
     *
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency", int.class));
        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency", int.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.direct.prefetch", int.class));
        return factory;
    }


    @Bean()
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate() {

        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
                System.out.println("ConfirmCallback:     " + "确认情况：" + ack);
                System.out.println("ConfirmCallback:     " + "原因：" + cause);
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("ReturnCallback:     " + "消息：" + message);
                System.out.println("ReturnCallback:     " + "回应码：" + replyCode);
                System.out.println("ReturnCallback:     " + "回应信息：" + replyText);
                System.out.println("ReturnCallback:     " + "交换机：" + exchange);
                System.out.println("ReturnCallback:     " + "路由键：" + routingKey);
            }
        });

        return rabbitTemplate;
    }


    /**
     * 定义队列 并做持久化处理
     *
     * @return
     */
    @Bean(name = "logUserQueue")
    public Queue logUserQueue() {
        return new Queue(env.getProperty("log.user.direct.queue"), true);
    }

    /**
     * 定义交换器
     * true:持久化处理
     * false:不进行自动删除
     *
     * @return
     */
    @Bean
    public DirectExchange logUserExchange() {
        return new DirectExchange(env.getProperty("log.user.direct.exchange"), true, false);
    }

    //路由
    @Bean
    public Binding logUserBinding() {
        //链式写法: 用指定的路由键将队列绑定到交换机
        return BindingBuilder.bind(logUserQueue())
                .to(logUserExchange())
                .with(env.getProperty("log.user.direct.routing.key"));
    }

    //TODO 用户操作日志消息模型  Fanout模式

    /**
     * 定义交换器
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        log.info("创建FanoutExchange>>>>>>>>>>>>>>>>>>>>>>>>>>>成功");
        return new FanoutExchange(env.getProperty("log.user.fanout.exchange"), true, false);
    }

    /**
     * 测试队列一
     *
     * @return 队列实例
     */
    @Bean
    public Queue queue1() {
        log.info("【【【测试队列一实例创建成功】】】");
        return new Queue(env.getProperty("log.user.fanout.queue1"));
    }

    /**
     * 测试队列二
     *
     * @return 队列实例
     */
    @Bean
    public Queue queue2() {
        log.info("【【【测试队列二实例创建成功】】】");
        return new Queue(env.getProperty("log.user.fanout.queue2"));
    }

    /**
     * 绑定队列一到交换机
     *
     * @return 绑定对象
     */
    @Bean
    public Binding bingQueue1ToExchange() {
        log.info("【【【绑定队列一到交换机成功】】】");
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    /**
     * 绑定队列二到交换机
     *
     * @return 绑定对象
     */
    @Bean
    public Binding bingQueue2ToExchange() {
        log.info("【【【绑定队列二到交换机成功】】】");
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }

    //TODO  用户操作日志消息模型  Topic模式
    @Bean
    public TopicExchange topicExchange() {
        log.info("【<<<<<<<<<<<--Topic交换器创建成功-->>>>>>>>>>>>>>>>>>>>】");
        return new TopicExchange(env.getProperty("log.user.topic.exchange"), true, false);
    }

    /**
     * 测试队列
     *
     * @return 队列实例
     */
    @Bean
    public Queue topicQueue() {
        log.info("【<<<<<<<<<<<--测试topic队列创建成功-->>>>>>>>>>>>>>>>>>>>】");
        return new Queue(env.getProperty("log.user.topic.queue"));
    }

    @Bean
    public Binding bingQueue2ToTopic() {
        Binding binding = BindingBuilder.bind(topicQueue()).to(topicExchange()).with("*.user.*");
        log.info("【<<<<<<<<<<<--topic队列绑定成功--匹配规则:" + env.getProperty("log.user.topic.queue.routing.key") + ">>>>>>>>>>>>>>>>>>>>】");
        return binding;
    }

    //TODO  用户操作日志消息模型  Headers模式
    @Bean
    public HeadersExchange headersExchange() {
        log.info("【<<<<<<<<<<<--Headers交换器创建成功-->>>>>>>>>>>>>>>>>>>>】");
        return new HeadersExchange(env.getProperty("log.user.headers.exchange"), true, false);
    }

    @Bean
    public Queue headers_queue() {
        return new Queue(env.getProperty("log.user.headers.queue"), true);
    }

    @Bean
    Binding bindingExchangeTopicQueue2() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", "101");
        map.put("pwd", "202");

        //whereAll表示全部匹配
        //return BindingBuilder.bind(queue).to(headersExchange).whereAll(map).match();

        //whereAny表示部分匹配
        //all: 默认值。一个传送消息的header里的键值对和交换机的header键值对全部匹配，才可以路由到对应交换机
        //any: 一个传送消息的header里的键值对和交换机的header键值对任意一个匹配，就可以路由到对应交换机
        return BindingBuilder.bind(headers_queue()).
                to(headersExchange()).
                whereAny(map).match();
    }
}
