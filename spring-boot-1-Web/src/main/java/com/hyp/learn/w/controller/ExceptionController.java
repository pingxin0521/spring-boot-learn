package com.hyp.learn.w.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.w.controller
 * hyp create at 19-12-21
 **/
@Controller()
@RequestMapping("/exception")
public class ExceptionController {
    /**
     * 传统项目中的界面
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/page")
    public String page() throws Exception {
        System.out.println("hello");
        throw new Exception("发生了界面错误");
    }
}
