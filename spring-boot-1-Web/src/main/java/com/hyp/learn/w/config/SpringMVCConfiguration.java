package com.hyp.learn.w.config;

import com.hyp.learn.w.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//使用WebMvcConfigurer可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {

    // SpringMVCConfiguration.java

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        //浏览器发送 /pingxin 请求来到 success
        registry.addViewController("/pingxin").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("main");
    }


    @Bean
    public LocaleResolver localeResolver() {

        return new MyLocaleResolver();
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
        //静态资源；  *.css , *.js
        //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/index.html","/","/user/login");
    }


//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        // 增加 XML 消息转换器
//        Jackson2ObjectMapperBuilder xmlBuilder = Jackson2ObjectMapperBuilder.xml();
//        xmlBuilder.indentOutput(true);
//        converters.add(new MappingJackson2XmlHttpMessageConverter(xmlBuilder.build()));
//    }


//    //注册三大组件
//    @Bean
//    public ServletRegistrationBean myServlet(){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyHttpServlet(),"/myServlet");
//        registrationBean.setLoadOnStartup(1);
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean myFilter(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new FirstFilter());
//        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
//        return registrationBean;
//    }
//
//    @Bean
//    public ServletListenerRegistrationBean myListener(){
//        ServletListenerRegistrationBean<MyServletContextListener> registrationBean = new ServletListenerRegistrationBean<>(new MyServletContextListener());
//        return registrationBean;
//    }

    //向IoC容器中添加servlet容器工厂定制器
    //Tomcat的配置TomcatServletWebServerFactory，另外还有Jetty、Undertow、Netty
    //如果自定义,则需要继承AbstractServletWebServerFactory
//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> myWebServerFactoryCustomizer() {
//
//        return factory -> {
//            //设置相关配置
//            factory.setPort(8082);
//        };
//    }
//
//    //向IoC容器中添加servlet容器工厂
//    @Bean
//    public ConfigurableServletWebServerFactory myConfigurableServletWebServerFactory() {
//
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.setPort(8083);
//        return factory;
//    }

}
