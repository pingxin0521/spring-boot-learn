package com.hyp.learn.mq.conf;


import com.hyp.learn.mq.receiver.DirectReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 和生产者的消息确认机制不同，因为消息接收本来就是在监听消息，符合条件的消息就会消费下来。
 * 所以，消息接收的确认机制主要存在三种模式：
 *
 * ①自动确认， 这也是默认的消息确认情况。   AcknowledgeMode.NONE
 * RabbitMQ成功将消息发出（即将消息成功写入TCP Socket）中立即认为本次投递已经被正确处理，不管消费者端是否成功处理本次投递。
 * 所以这种情况如果消费端消费逻辑抛出异常，也就是消费端没有处理成功这条消息，那么就相当于丢失了消息。
 * 一般这种情况我们都是使用try catch捕捉异常后，打印日志用于追踪数据，这样找出对应数据再做后续处理。
 *
 * ② 不确认， 这个不做介绍
 * ③ 手动确认 ， 这个比较关键，也是我们配置接收消息确认机制时，多数选择的模式。
 * 消费者收到消息后，手动调用basic.ack/basic.nack/basic.reject后，RabbitMQ收到这些消息后，才认为本次投递成功。
 * basic.ack用于肯定确认 
 * basic.nack用于否定确认（注意：这是AMQP 0-9-1的RabbitMQ扩展） 
 * basic.reject用于否定确认，但与basic.nack相比有一个限制:一次只能拒绝单条消息 
 * 消费者端以上的3个方法都表示消息已经被正确投递，但是basic.ack表示消息已经被正确处理，但是basic.nack,basic.reject表示没有被正确处理，但是RabbitMQ中仍然需要删除这条消息。 
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mq.conf
 * hyp create at 20-1-22
 **/
@Configuration
public class MessageListenerConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private DirectReceiver directReceiver;//Direct消息接收处理类
    //    @Autowired
//    FanoutReceiverA fanoutReceiverA;//Fanout消息接收处理类A
    @Autowired
    DirectRabbitConfig directRabbitConfig;

    //    @Autowired
//    FanoutRabbitConfig fanoutRabbitConfig;
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
        container.setQueues(directRabbitConfig.TestDirectQueue());
        container.setMessageListener(directReceiver);
//        container.addQueues(fanoutRabbitConfig.queueA());
//        container.setMessageListener(fanoutReceiverA);
        return container;
    }
}
