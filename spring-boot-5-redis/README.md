----

### 项目阐述——redis

Spring Boot 提供了对 Redis 集成的组件包：spring-boot-starter-data-redis，spring-boot-starter-data-redis依赖于spring-data-redis 和 lettuce 。Spring Boot 1.0 默认使用的是 Jedis 客户端，2.0 替换成 Lettuce，但如果你从 Spring Boot 1.5.X 切换过来，几乎感受不大差异，这是因为 spring-boot-starter-data-redis 为我们隔离了其中的差异性。



Lettuce 是一个可伸缩线程安全的 Redis 客户端，多个线程可以共享同一个 RedisConnection，它利用优秀 netty NIO 框架来高效地管理多个连接。


#### 分布式锁

为了确保分布式锁可用，我们至少要确保锁的实现同时满足以下四个条件：

- 互斥性。在任意时刻，只有一个客户端能持有锁。
- 不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
- 具有容错性。只要大部分的Redis节点正常运行，客户端就可以加锁和解锁。
- 解铃还须系铃人。加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了。

关于分布式锁，有两点想说：

- requestID的问题，为什么要设置一个唯一性id作为redis的value呢？不知道有没有想过这个问题，原因是这样的，每次调度程序会生成一个id作为value尝试从redis拿锁，如果拿到的话执行下面的业务job,等执行完会去redis释放锁，释放锁的依据是什么呢？就是这个请求id。简单点说，这样做主要是为了避免误删除别的客户端加的锁
- redis为啥能实现锁唯一，保证同一时间只有一个程序能拿到锁？因为加锁解锁操作是通过lua脚本实现的，而redis有个特性就是在eval命令执行Lua代码的时候，Lua代码将被当成一个命令去执行，并且直到eval命令执行完成，Redis才会执行其他命令。

