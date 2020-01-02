package com.hyp.learn.jpa.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.jpa.listener
 * hyp create at 20-1-2
 **/
public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializer...initialize..." + applicationContext);
    }
}
