package com.hyp.learn.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock-mvc")
public class UserController {


    @GetMapping("/test-get")
    public String testHello () {
        return "hello";
    }
}
