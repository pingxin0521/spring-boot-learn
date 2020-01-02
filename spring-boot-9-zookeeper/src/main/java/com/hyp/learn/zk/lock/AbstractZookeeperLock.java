package com.hyp.learn.zk.lock;

import java.util.concurrent.TimeUnit;

/**
 * 公用锁抽象类
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.zk.lock
 * hyp create at 20-1-2
 **/
public abstract class AbstractZookeeperLock<T> {
    private static final int TIME_OUT = 5;

    public abstract String getLockPath();

    public abstract T execute();

    public int getTimeout(){
        return TIME_OUT;
    }

    public TimeUnit getTimeUnit(){
        return TimeUnit.SECONDS;
    }
}
