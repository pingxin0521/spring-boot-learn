package com.hyp.learn.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.starter
 * hyp create at 20-1-2
 **/
@ConfigurationProperties("pingxin.hello")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
