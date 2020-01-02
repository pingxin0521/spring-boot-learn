package com.hyp.learn.zk.conf;

import com.hyp.learn.zk.lock.DistributedLock;
import com.hyp.learn.zk.lock.DistributedLockImpl;
import com.hyp.learn.zk.lock.ZookeeperClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

/**
 * https://blog.csdn.net/wangaiheng/article/details/82259211
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.zk.conf
 * hyp create at 20-1-2
 **/
@Configuration
@PropertySources(value = {@PropertySource("classpath:zookeeper.properties")})
public class ZookeeperConfig {

    @Autowired
    private Environment environment;

    @Autowired
    private ZookeeperClient zookeeperClient;

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ZookeeperClient zookeeperClient() {
        String zookeeperServer = environment.getRequiredProperty("zookeeper.server");
        String zookeeperLockPath = environment.getRequiredProperty("zookeeper.lockPath");
        return new ZookeeperClient(zookeeperServer, zookeeperLockPath);
    }

    @Bean
    public DistributedLock distributedLock()
    {
        return new DistributedLockImpl(zookeeperClient.getClient());
    }
}

