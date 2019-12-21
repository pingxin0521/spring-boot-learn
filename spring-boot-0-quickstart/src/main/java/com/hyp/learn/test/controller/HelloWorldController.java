package com.hyp.learn.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.qs.controller
 * hyp create at 19-12-21
 **/
@RestController
public class HelloWorldController {
    /**
     * The class is flagged as a @RestController,meaning it’s ready for use by Spring MVC to handle web requests.
     *
     * @RequestMapping maps / to the index() method. When invoked(调用) from a browser or using curl on the command line,
     * the method returns pure(单纯的) text.
     * <p>
     * That’s because @RestController combines(结合) @Controller and @ResponseBody,
     * two annotations that results in web requests returning data rather than a view.
     * <p>
     * 那是因为@RestController联合了@Controller and @ResponseBody两个注解导致返回的是数据而不是视图
     */

    @RequestMapping("/hello")
    public String index() {
        System.out.println("Hello Spring-Boot");
        return "Hello World";
    }
}
