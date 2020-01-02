package com.hyp.learn.zk.lock;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.zk.lock
 * hyp create at 20-1-2
 **/
public enum  BusinessTypeEnum {
    items("/items"),
    orders("/orders");
    private String value;
    BusinessTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}