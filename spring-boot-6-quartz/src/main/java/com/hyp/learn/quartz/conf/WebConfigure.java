package com.hyp.learn.quartz.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.conf
 * hyp create at 19-12-31
 **/
@Configuration
public class WebConfigure implements WebMvcConfigurer {
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController("/manager").setViewName("JobManager");

        registry.addViewController("/").setViewName("login");

    }
}
