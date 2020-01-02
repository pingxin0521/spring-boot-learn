package com.hyp.learn.zk.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.zk.lock
 * hyp create at 20-1-2
 **/
public class AbstractCuatorExt implements CuatorExt {
    final static Logger lg = LoggerFactory.getLogger(AbstractCuatorExt.class);
    public CuratorFramework client;
    AbstractCuatorExt(CuratorFramework client){
        this.client = client;
    }

    public CuratorFramework getClient() {
        return client;
    }

    @Override
    public String createEphemeralSequential(String basePath) throws Exception {
        String o = this.client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(basePath);
        return o;
    }

    @Override
    public void delete(String ourPath) throws Exception {
        this.client.delete().forPath(ourPath);
    }

    @Override
    public List<String> getChildren(String basePath) throws Exception {
        List<String> children = this.client.getChildren().forPath(basePath);
        return children;
    }
}