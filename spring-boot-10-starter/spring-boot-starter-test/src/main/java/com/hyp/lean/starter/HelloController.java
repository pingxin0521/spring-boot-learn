package com.hyp.lean.starter;

import com.hyp.learn.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.lean.starter
 * hyp create at 20-1-2
 **/
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "平心") String name) {
        return helloService.sayHello(name);
    }
}
