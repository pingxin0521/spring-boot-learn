package com.hyp.learn.w.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {

    // SpringMVCConfiguration.java

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 增加 XML 消息转换器
        Jackson2ObjectMapperBuilder xmlBuilder = Jackson2ObjectMapperBuilder.xml();
        xmlBuilder.indentOutput(true);
        converters.add(new MappingJackson2XmlHttpMessageConverter(xmlBuilder.build()));
    }

}
