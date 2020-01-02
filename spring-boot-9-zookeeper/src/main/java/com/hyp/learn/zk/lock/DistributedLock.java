package com.hyp.learn.zk.lock;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.zk.lock
 * hyp create at 20-1-2
 **/

import java.util.concurrent.TimeUnit;

/**
 * 分配锁
 */
public  interface DistributedLock {

    /**获取锁，如果没有得到就等待*/
    public  String acquire(BusinessTypeEnum businessType)  throws Exception;

    /**
     * 获取锁，直到超时
     * @param time 超时时间
     * @param unit time参数的单位
     * @return是否获取到锁
     * @throws Exception
     */
    public String acquire(BusinessTypeEnum businessType,long time, TimeUnit unit)  throws Exception;

    /**
     * 释放锁
     * @throws Exception
     */
    public void release(String lockName)  throws Exception;


}
