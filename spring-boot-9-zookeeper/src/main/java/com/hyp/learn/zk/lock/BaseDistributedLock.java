package com.hyp.learn.zk.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.zk.lock
 * hyp create at 20-1-2
 **/
public class BaseDistributedLock extends AbstractCuatorExt {
    private static final String NAME_SPACE="lock_namespace";
    private static final String DISTRIBUTED_LOCK = "/lock-";
    BaseDistributedLock(CuratorFramework client) {
        super(client);
    }
    private static final Integer MAX_RETRY_COUNT = 10;//重试次数
    public void init(){
        this.client = this.client.usingNamespace(NAME_SPACE);
        for(BusinessTypeEnum b : BusinessTypeEnum.values()){
            try {
                if(this.client.checkExists().forPath(b.getValue())==null){
                    this.client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(b.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取锁，并设置超时时间
     * @param time
     * @param timeUnit
     * @param businessType
     * @return
     * @throws Exception
     */
    protected String attemptLock(long time, TimeUnit timeUnit, BusinessTypeEnum businessType) throws Exception {
        boolean goDone = false;
        String ourPath = null;
        String lockName = null;
        long startMillis = System.currentTimeMillis();
        int count = 0;
        while (!goDone) {
            goDone = true;
            try {
                ourPath = createEphemeralSequential(businessType.getValue()+DISTRIBUTED_LOCK);
                lockName = waitToLock(startMillis, time, timeUnit, businessType, ourPath);
            } catch (Exception e) {
                if (count++ < MAX_RETRY_COUNT) {
                    goDone = false;
                } else {
                    throw e;
                }
            }
        }
        return lockName;
    }

    private String waitToLock(long startMillis, long time, TimeUnit timeUnit, BusinessTypeEnum businessType, String ourPath) throws Exception {
        boolean haveLock = false;
        String lockName = null;
        Long waitMillis = timeUnit == null ? null : timeUnit.toMillis(time);
        boolean doDelete = false;
        try {
            while (!haveLock) {
                List<String> children = getChildrenAndSortThem(businessType.getValue());
                int index = children.indexOf(ourPath.substring(( businessType.getValue() + "/").length()));
                if (index < 0) {
                    throw new Exception("无此节点：" + ourPath);
                }
                if (index == 0) {
                    haveLock = true;
                    lockName = ourPath;
                } else {
                    String frontPath = children.get(index-1);
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    getClient().getData().usingWatcher(new CuratorWatcher() {
                        @Override
                        public void process(WatchedEvent watchedEvent) throws Exception {
                            countDownLatch.countDown();
                            lg.info(frontPath + "完成");
                        }
                    }).forPath(businessType.getValue()+"/"+frontPath);
                    if(waitMillis!=null){
                        waitMillis = System.currentTimeMillis() -  startMillis;
                        if(waitMillis>0){
                            lg.info(ourPath + "等待" + frontPath + "完成");
                            countDownLatch.await(waitMillis,timeUnit);
                        }else{
                            throw new Exception(ourPath+"等待超时");
                        }
                    }else{
                        lg.info(ourPath + "等待" + frontPath + "完成");
                        countDownLatch.await();
                    }
                    startMillis = System.currentTimeMillis();
                }
            }
        } catch (Exception e) {
            doDelete = true;
            throw e;
        }finally {
            if(doDelete){
                delete(ourPath);
            }
        }
        return lockName;
    }

    private List<String> getChildrenAndSortThem(String basePath) {
        List<String> children = null;
        try {
            children = getChildren(basePath);
            Collections.sort(children, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return getLockNumber(o1, basePath.length()) - getLockNumber(o2, basePath.length());
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return children;
    }

    private int getLockNumber(String node, int suff) {
        node = node.substring(suff);
        return Integer.parseInt(node);
    }
}
