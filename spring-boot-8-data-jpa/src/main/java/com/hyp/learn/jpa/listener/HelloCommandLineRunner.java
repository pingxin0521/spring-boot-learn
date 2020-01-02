package com.hyp.learn.jpa.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.jpa.listener
 * hyp create at 20-1-2
 **/
@Component
public class HelloCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner...run..."+ Arrays.asList(args));
    }
}
