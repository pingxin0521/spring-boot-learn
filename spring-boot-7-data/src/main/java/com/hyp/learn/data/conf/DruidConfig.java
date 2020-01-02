package com.hyp.learn.data.conf;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.qs.conf
 * hyp create at 20-1-1
 **/
@Configuration
public class DruidConfig {
    //配置监控
    //1.配置管理后台的servlet
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {

        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(
                new StatViewServlet(), "/druid/*"
        );

        //配置初始化参数
        //com.alibaba.druid.support.http.StatViewServlet
//        com.alibaba.druid.support.http.ResourceServlet
        Map<String, String> data = new HashMap<>();
        data.put("loginUsername", "admin");
        data.put("loginPassword", "123456");
        registrationBean.setInitParameters(data);


        return registrationBean;
    }

    //2.配置一个监控的filter
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        //配置初始化参数
//        com.alibaba.druid.support.http.WebStatFilter
        Map<String, String> data = new HashMap<>();
        data.put("exclusions", "*.js,*.css,/druid/*");

        bean.setInitParameters(data);

        bean.setUrlPatterns(Collections.singletonList("/*"));
        return bean;
    }
}
