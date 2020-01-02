package com.hyp.learn.zk.lock;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.zk.lock
 * hyp create at 20-1-2
 **/
public class DistributedLockImpl extends BaseDistributedLock implements DistributedLock {

    public DistributedLockImpl(CuratorFramework client) {
        super(client);
    }

    @Override
    public String acquire(BusinessTypeEnum businessType) throws Exception {
        return attemptLock(0,null,businessType);
    }

    @Override
    public String acquire(BusinessTypeEnum businessType, long time, TimeUnit unit) throws Exception {
        return attemptLock(time,unit,businessType);
    }

    @Override
    public void release(String lockName) throws Exception {
        delete(lockName);
    }
}