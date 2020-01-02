package com.hyp.learn.zk.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.zk.lock
 * hyp create at 20-1-2
 **/
@Service("byService")
public class ByServiceImpl implements BuyService {
    static int i = 0;
    Logger lg = LoggerFactory.getLogger(ByServiceImpl.class);
//    @Autowired
//    OrderService orderService;
//    @Autowired
//    ItemService itemService;
    @Autowired
    DistributedLock distributedLock;
    @Override
    public String getLock(String name) {
        lg.info("开始获取锁");
        String lockName = null;
        try {
            lockName = distributedLock.acquire(BusinessTypeEnum.items);
            lg.info(lockName + "进行业务中:");
            Thread.sleep(30000);
            distributedLock.release(lockName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        lg.info(lockName+"释放完毕");
        return lockName;
    }
}
