package com.hyp.learn.quartz.conf;

import com.hyp.learn.quartz.listener.SimpleTriggerListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.conf
 * hyp create at 19-12-29
 **/
@Configuration
public class SchedulerConfig {
//    @Bean(name="SchedulerFactory")
//    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setQuartzProperties(quartzProperties());
//        return factory;
//    }
//
//    @Bean
//    public Properties quartzProperties() throws IOException {
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
//        //在quartz.properties中的属性被读取并注入后再初始化对象
//        propertiesFactoryBean.afterPropertiesSet();
//        return propertiesFactoryBean.getObject();
//    }

    /*
     * quartz初始化监听器
     */
//    @Bean
//    public QuartzInitializerListener executorListener() {
//        return new QuartzInitializerListener();
//    }
//
//    /*
//     * 通过SchedulerFactoryBean获取Scheduler的实例
//     */
//    @Bean(name="Scheduler")
//    public Scheduler scheduler() throws IOException {
//        return schedulerFactoryBean().getScheduler();
//    }

    @Autowired
    private SchedulerFactoryBean factoryBean;

    @Autowired
    private SimpleTriggerListener simpleTriggerListener;

    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name = "Scheduler")
    public Scheduler scheduler() throws IOException, SchedulerException {
        Scheduler scheduler = factoryBean.getScheduler();
        scheduler.getListenerManager()
                .addTriggerListener(simpleTriggerListener);
        System.out.println(scheduler.getListenerManager().getTriggerListeners());
        return scheduler;
    }


}
