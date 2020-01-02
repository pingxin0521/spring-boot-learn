package com.hyp.learn.zk.lock;

import java.util.List;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.zk.lock
 * hyp create at 20-1-2
 **/
public interface CuatorExt {
    /**
     * 创建临时序列节点
     * @param basePath
     * @return
     */
    public String createEphemeralSequential(String basePath) throws Exception;

    /**
     * 删除节点
     * @param ourPath
     */
    public void delete(String ourPath) throws Exception;

    /**
     * 获取子节点
     * @param basePath
     * @return
     */
    public List<String> getChildren(String basePath) throws Exception;

}