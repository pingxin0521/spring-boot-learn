package com.hyp.learn.wf.config;

import com.hyp.learn.wf.commons.GlobalResponseBodyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.wf.config
 * hyp create at 19-12-22
 **/
@Configuration
@EnableWebFlux
public class WebFluxConfiguration {

    @Bean
    public GlobalResponseBodyHandler responseWrapper(ServerCodecConfigurer serverCodecConfigurer,
                                                     RequestedContentTypeResolver requestedContentTypeResolver) {
        return new GlobalResponseBodyHandler(serverCodecConfigurer.getWriters(), requestedContentTypeResolver);
    }

}
