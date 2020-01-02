package com.hyp.learn.zk.lock;

import lombok.Getter;

/**
 * 新建测试锁类
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.zk.lock
 * hyp create at 20-1-2
 **/
public abstract class TestLock<String> extends AbstractZookeeperLock<String> {
    private static final java.lang.String LOCK_PATH = "test_";

    @Getter
    private String lockId;

    public TestLock(String lockId) {
        this.lockId = lockId;
    }

    @Override
    public java.lang.String getLockPath() {
        return LOCK_PATH + this.lockId;
    }
}
