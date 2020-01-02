package com.hyp.learn.jpa.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.jpa.listener
 * hyp create at 20-1-2
 **/
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {
    public HelloSpringApplicationRunListener(SpringApplication application, String[] args) {

    }

    @Override
    public void starting() {
        System.out.println("SpringApplicationRunListener...starting...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        Object o = environment.getSystemProperties().get("os.name");
        System.out.println("SpringApplicationRunListener...environmentPrepared.." + o);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...contextPrepared...");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...contextLoaded...");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...started..." + context);
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...running..." + context);
    }
}
