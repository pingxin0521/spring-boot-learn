package com.hyp.learn.starter;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.starter
 * hyp create at 20-1-2
 **/
public class HelloService {

    HelloProperties helloProperties;

    public String sayHello(String name) {
        return helloProperties.getPrefix() + "-" + name + "-" + helloProperties.getSuffix();
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
