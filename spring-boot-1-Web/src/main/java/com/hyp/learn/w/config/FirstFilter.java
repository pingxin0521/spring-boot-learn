package com.hyp.learn.w.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.w.config
 * hyp create at 19-12-21
 **/
@WebFilter(filterName="FirstFilter", urlPatterns="/first") //urlPatterns是一个数组，可以配置拦截多个。urlPatterns= {"*.do","*.jsp"}
public class FirstFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        System.out.println("通过注解方式进入拦截器..............");
        arg2.doFilter(arg0, arg1);
        System.out.println("拦截器结束..............");

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
