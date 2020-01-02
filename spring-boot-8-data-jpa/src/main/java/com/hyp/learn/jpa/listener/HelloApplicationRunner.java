package com.hyp.learn.jpa.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.jpa.listener
 * hyp create at 20-1-2
 **/
@Component
public class HelloApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner...run....");
    }
}
