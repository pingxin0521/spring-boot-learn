----

### 项目阐述——quartz示例

本工程所用到的技术或工具有：

- Spring Boot
- Mybatis
- Quartz
- PageHelper
- VueJS
- ElementUI
- MySql数据库
- Druid数据库连接池:<https://github.com/drtrang/druid-spring-boot>
- thymeleaf
- redis:基于redis分布式锁实现quartz调度集群

对于Cron表达式，可以去[在线Cron表达式生成器](http://cron.qqe2.com/)根据自己的需求自动生成。



#### 运行方法

Spring Boot工程已经集成了服务器。右键点击DemoApplication.java -> Run As -> Java Application即可运行工程。默认端口为8080，启动后在浏览器地址栏输入<http://localhost:8080/quartz/manager>就可以看到效果。

[常用数据库脚本](./dbTables),quartz版本为2.2.3,可以选择对应的sql脚本执行，用作存储定是个hi任务数据

运行Application main方法启动项目，项目启动会自动创建一个测试任务 见：com.itstyle.quartz.config.TaskRunner.java。
- 项目访问地址：<http://localhost:8080/quartz>



#### 项目截图


![表达式生成器](https://gitee.com/uploads/images/2018/0402/180033_437a1186_87650.png "7.png")

#### 集群测试

打开quartz集群配置：
```
# 打开集群配置
spring.quartz.properties.org.quartz.jobStore.isClustered:true
# 设置集群检查间隔20s
spring.quartz.properties.org.quartz.jobStore.clusterCheckinInterval = 2000 
```

测试命令
```bash
mvn clean package
java -Dserver.port=8081 -jar target/spring-boot-6-quartz.jar 
java -Dserver.port=8081-jar  target/spring-boot-6-quartz.jar 
```

### 数据库表

Quartz持久化到数据库中各表字段详解（以MYSQL数据库为例）

**QRTZ_BLOB_TRIGGERS**：自定义触发器

Trigger 作为 Blob 类型存储(用于 Quartz 用户用JDBC创建他们自己定制的 Trigger 类型，JobStore并不知道如何存储实例的时候) 

| 表名                  | 表说明     | 自定义触发器                          |          |          |                       |        |
| --------------------- | ---------- | ------------------------------------- | -------- | -------- | --------------------- | ------ |
| QRTZ_BLOB_TRIGGERS    | 列名（英） | 列名（中）                            | 数据类型 | 列长度   | 是否为空              | 列说明 |
| SCHED_NAME            | 计划名称   | nvarchar                              | 100      | not null | 主键                  |        |
| TRIGGER_NAME          | 触发器名称 | nvarchar                              | 150      | not null | 主键                  |        |
| TRIGGER_GROUP         | 触发器组   | nvarchar                              | 150      | not null | 主键                  |        |
| BLOB_DATA             |            | image                                 |          | null     | 保存triggers 一些信息 |        |
| 约束                  | 约束名     | 约束类型                              | 约束字段 |          |                       |        |
| PK_QRTZ_BLOB_TRIGGERS | 主键       | SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP |          |          |                       |        |

**QRTZ_CALENDARS**

以 Blob 类型存储 Quartz 的 Calendar 信息

| 表名              | 表说明     | 以 Blob 类型存储 Quartz 的 Calendar 信息 |              |          |                       |        |
| ----------------- | ---------- | ---------------------------------------- | ------------ | -------- | --------------------- | ------ |
| QRTZ_CALENDARS    | 列名（英） | 列名（中）                               | 数据类型     | 列长度   | 是否为空              | 列说明 |
| SCHED_NAME        | 计划名称   | nvarchar                                 | 100          | not null | 主键                  |        |
| CALENDAR_NAME     | 触发器名称 | nvarchar                                 | 200          | not null | 主键                  |        |
| CALENDAR          |            | image                                    |              | not null | 保存Calendar 一些信息 |        |
| **约束**          | **约束名** | **约束类型**                             | **约束字段** |          |                       |        |
| PK_QRTZ_CALENDARS | 主键       | SCHED_NAME,CALENDAR_NAME                 |              |          |                       |        |

**QRTZ_CRON_TRIGGERS**

存储 Cron Trigger，包括Cron表达式和时区信息

| 表名                  | 表说明     | 存储 Cron Trigger，包括Cron表达式和时区信息 |              |          |                                                              |        |
| --------------------- | ---------- | ------------------------------------------- | ------------ | -------- | ------------------------------------------------------------ | ------ |
| QRTZ_CRON_TRIGGERS    | 列名（英） | 列名（中）                                  | 数据类型     | 列长度   | 是否为空                                                     | 列说明 |
| SCHED_NAME            | 计划名称   | nvarchar                                    | 100          | not null | 主键                                                         |        |
| TRIGGER_NAME          | 触发器名称 | nvarchar                                    | 150          | not null | 主键                                                         |        |
| TRIGGER_GROUP         | 触发器组   | nvarchar                                    | 150          | not null | 主键                                                         |        |
| TIME_ZONE_ID          | 时区ID     | nvarchar                                    | 80           |          | 时区如：China Standard Time                                  |        |
| CRON_EXPRESSION       | 时间表达式 | nvarchar                                    | 120          | null     | 定时设置如：0 0 12 * * ? 每天12点触发，0 15 10 ? * * 每天10点15分触发 |        |
| **约束**              | **约束名** | **约束类型**                                | **约束字段** |          |                                                              |        |
| PK_QRTZ_CRON_TRIGGERS | 主键       | SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP       |              |          |                                                              |        |

**QRTZ_FIRED_TRIGGERS**

存储与已触发的 Trigger 相关的状态信息，以及相联 Job的执行信息QRTZ_PAUSED_TRIGGER_GRPS 存储已暂停的 Trigger组的信息

| 表名                   | 表说明         | 保存已经触发的触发器状态信息 |              |          |          |        |
| ---------------------- | -------------- | ---------------------------- | ------------ | -------- | -------- | ------ |
| QRTZ_FIRED_TRIGGERS    | 列名（英）     | 列名（中）                   | 数据类型     | 列长度   | 是否为空 | 列说明 |
| SCHED_NAME             | 计划名称       | nvarchar                     | 100          | not null | 主键     |        |
| ENTRY_ID               | 组标识         | nvarchar                     | 95           | not null | 主键     |        |
| TRIGGER_NAME           | 触发器名称     | nvarchar                     | 150          | not null |          |        |
| TRIGGER_GROUP          | 触发器组       | nvarchar                     | 150          | not null |          |        |
| INSTANCE_NAME          | 当前实例的名称 | nvarchar                     | 200          | not null |          |        |
| FIRED_TIME             | 当前执行时间   | bigint                       |              | not null |          |        |
| SCHED_TIME             | 计划时间       | bigint                       |              | not null |          |        |
| PRIORITY               | 权重           | int                          |              | not null |          |        |
| STATE                  | 状态           | nvarchar                     | 16           | not null |          |        |
| JOB_NAME               | 作业名称       | nvarchar                     | 150          | null     |          |        |
| JOB_GROUP              | 作业组         | nvarchar                     | 150          | null     |          |        |
| IS_NONCONCURRENT       | 是否并行       | bit                          |              | null     |          |        |
| REQUESTS_RECOVERY      | 是否要求唤醒   | bit                          | 80           | null     |          |        |
| **约束**               | **约束名**     | **约束类型**                 | **约束字段** |          |          |        |
| PK_QRTZ_FIRED_TRIGGERS | 主键           | SCHED_NAME,ENTRY_ID          |              |          |          |        |

**QRTZ_JOB_DETAILS**

存储每一个已配置的 Job 的详细信息

| 表名                | 表说明         | job 详细信息                  |              |          |          |        |
| ------------------- | -------------- | ----------------------------- | ------------ | -------- | -------- | ------ |
| QRTZ_JOB_DETAILS    | 列名（英）     | 列名（中）                    | 数据类型     | 列长度   | 是否为空 | 列说明 |
| SCHED_NAME          | 计划名称       | nvarchar                      | 100          | not null | 主键     |        |
| JOB_NAME            | 作业名称       | nvarchar                      | 150          | not null | 主键     |        |
| JOB_GROUP           | 作业组         | nvarchar                      | 150          | not null | 主键     |        |
| DESCRIPTION         | 描述           | nvarchar                      | 150          | not null |          |        |
| JOB_CLASS_NAME      | 作业程序集名称 | nvarchar                      | 200          | not null |          |        |
| IS_DURABLE          | 是否持久       | bit                           |              | not null |          |        |
| IS_NONCONCURRENT    | 是否并行       | bit                           |              | not null |          |        |
| IS_UPDATE_DATA      | 是否更新       | bit                           |              | not null |          |        |
| REQUESTS_RECOVERY   | 是否要求唤醒   | bit                           |              | not null |          |        |
| JOB_DATA            | 作业名称       | image                         |              | null     |          |        |
| **约束**            | **约束名**     | **约束类型**                  | **约束字段** |          |          |        |
| PK_QRTZ_JOB_DETAILS | 主键           | SCHED_NAME,JOB_NAME,JOB_GROUP |              |          |          |        |

**QRTZ_LOCKS**

存储程序的悲观锁的信息(假如使用了悲观锁)

| 表名          | 表说明     | 存储程序的悲观锁的信息(假如使用了悲观锁) |              |          |          |        |
| ------------- | ---------- | ---------------------------------------- | ------------ | -------- | -------- | ------ |
| QRTZ_LOCKS    | 列名（英） | 列名（中）                               | 数据类型     | 列长度   | 是否为空 | 列说明 |
| SCHED_NAME    | 计划名称   | nvarchar                                 | 100          | not null | 主键     |        |
| LOCK_NAME     | 锁名称     | nvarchar                                 | 40           | not null | 主键     |        |
| **约束**      | **约束名** | **约束类型**                             | **约束字段** |          |          |        |
| PK_QRTZ_LOCKS | 主键       | SCHED_NAME,LOCK_NAME                     |              |          |          |        |

**QRTZ_PAUSED_TRIGGER_GRPS**

存放暂停掉的触发器

| 表名                        | 表说明     | 存放暂停掉的触发器       |              |          |          |        |
| --------------------------- | ---------- | ------------------------ | ------------ | -------- | -------- | ------ |
| QRTZ_PAUSED_TRIGGER_GRPS    | 列名（英） | 列名（中）               | 数据类型     | 列长度   | 是否为空 | 列说明 |
| SCHED_NAME                  | 计划名称   | nvarchar                 | 100          | not null | 主键     |        |
| TRIGGER_GROUP               | 触发器组   | nvarchar                 | 150          | not null | 主键     |        |
| **约束**                    | **约束名** | **约束类型**             | **约束字段** |          |          |        |
| PK_QRTZ_PAUSED_TRIGGER_GRPS | 主键       | SCHED_NAME,TRIGGER_GROUP |              |          |          |        |

**QRTZ_SCHEDULER_STATE**

存储少量的有关 Scheduler 的状态信息，和别的Scheduler实例(假如是用于一个集群中)

| 表名                    | 表说明         | 存储少量的有关 Scheduler 的状态信息，和别的 Scheduler 实例 |              |          |          |        |
| ----------------------- | -------------- | ---------------------------------------------------------- | ------------ | -------- | -------- | ------ |
| QRTZ_SCHEDULER_STATE    | 列名（英）     | 列名（中）                                                 | 数据类型     | 列长度   | 是否为空 | 列说明 |
| SCHED_NAME              | 计划名称       | nvarchar                                                   | 100          | not null | 主键     |        |
| INSTANCE_NAME           | 实例名称       | nvarchar                                                   | 200          | not null | 主键     |        |
| LAST_CHECKIN_TIME       | 最后的检查时间 | bigint                                                     |              | not null |          |        |
| CHECKIN_INTERVAL        | 检查间隔       | bigint                                                     |              | not null |          |        |
| **约束**                | **约束名**     | **约束类型**                                               | **约束字段** |          |          |        |
| PK_QRTZ_SCHEDULER_STATE | 主键           | SCHED_NAME,INSTANCE_NAME                                   |              |          |          |        |

**QRTZ_SIMPLE_TRIGGERS**

存储简单的Trigger，包括重复次数，间隔，以及已触的次数

| 表名                                  | 表说明     | 简单的触发器                            |                                                              |                    |          |        |
| ------------------------------------- | ---------- | --------------------------------------- | ------------------------------------------------------------ | ------------------ | -------- | ------ |
| QRTZ_SIMPLE_TRIGGERS                  | 列名（英） | 列名（中）                              | 数据类型                                                     | 列长度             | 是否为空 | 列说明 |
| SCHED_NAME                            | 计划名称   | nvarchar                                | 100                                                          | not null           | 主键     |        |
| TRIGGER_NAME                          | 触发器名称 | nvarchar                                | 150                                                          | not null           | 主键     |        |
| TRIGGER_GROUP                         | 触发器组   | nvarchar                                | 150                                                          | not null           | 主键     |        |
| REPEAT_COUNT                          | 重复次数   | int                                     |                                                              | not null           |          |        |
| TIMES_TRIGGERED                       | 触发次数   | int                                     |                                                              | not null           |          |        |
| REPEAT_INTERVAL                       | 重复间隔   | bigint                                  |                                                              | not null           |          |        |
| **约束**                              | **约束名** | **约束类型**                            | **约束字段**                                                 | **其它表约束字段** |          |        |
| FK_QRTZ_SIMPLE_TRIGGERS_QRTZ_TRIGGERS | 外键       | SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP | [QRTZ_TRIGGERS] ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP]) |                    |          |        |
| PK_QRTZ_SIMPLE_TRIGGERS               | 主键       | SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP   |                                                              |                    |          |        |

**QRTZ_SIMPROP_TRIGGERS**

| 表名                                   | 表说明     |                                                           |                                                              |                    |          |        |
| -------------------------------------- | ---------- | --------------------------------------------------------- | ------------------------------------------------------------ | ------------------ | -------- | ------ |
| QRTZ_SIMPROP_TRIGGERS                  | 列名（英） | 列名（中）                                                | 数据类型                                                     | 列长度             | 是否为空 | 列说明 |
| SCHED_NAME                             | 计划名称   | nvarchar                                                  | 100                                                          | not null           | 主键     |        |
| TRIGGER_NAME                           | 触发器名称 | nvarchar                                                  | 150                                                          | not null           | 主键     |        |
| TRIGGER_GROUP                          | 触发器组   | nvarchar                                                  | 150                                                          | not null           | 主键     |        |
| STR_PROP_1                             |            | nvarchar                                                  | 512                                                          | null               |          |        |
| STR_PROP_2                             |            | nvarchar                                                  | 512                                                          | null               |          |        |
| STR_PROP_3                             |            | nvarchar                                                  | 512                                                          | null               |          |        |
| INT_PROP_1                             |            | int                                                       |                                                              | null               |          |        |
| INT_PROP_2                             |            | int                                                       |                                                              | null               |          |        |
| LONG_PROP_1                            |            | bigint                                                    |                                                              | null               |          |        |
| LONG_PROP_2                            |            | bigint                                                    |                                                              | null               |          |        |
| DEC_PROP_1                             |            | numeric                                                   | (13,4)                                                       | null               |          |        |
| DEC_PROP_2                             |            | numeric                                                   | (13,4)                                                       | null               |          |        |
| BOOL_PROP_1                            |            | bit                                                       |                                                              | null               |          |        |
| BOOL_PROP_2                            |            | bit                                                       | 80                                                           | null               |          |        |
| **约束**                               | **约束名** | **约束类型**                                              | **约束字段**                                                 | **其它表约束字段** |          |        |
| PK_QRTZ_SIMPROP_TRIGGERS               | 主键       | [SCHED_NAME] ASC, [TRIGGER_NAME] ASC, [TRIGGER_GROUP] ASC |                                                              |                    |          |        |
| FK_QRTZ_SIMPROP_TRIGGERS_QRTZ_TRIGGERS | 外键       | [SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP]             | [QRTZ_TRIGGERS] ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP]) |                    |          |        |

**QRTZ_TRIGGERS**

触发器的基本信息

| 表名                              | 表说明         | 触发器的基本信息                                          |                                                            |                    |          |        |
| --------------------------------- | -------------- | --------------------------------------------------------- | ---------------------------------------------------------- | ------------------ | -------- | ------ |
| QRTZ_TRIGGERS                     | 列名（英）     | 列名（中）                                                | 数据类型                                                   | 列长度             | 是否为空 | 列说明 |
| SCHED_NAME                        | 计划名称       | nvarchar                                                  | 100                                                        | not null           | 主键     |        |
| TRIGGER_NAME                      | 触发器名称     | nvarchar                                                  | 150                                                        | not null           | 主键     |        |
| TRIGGER_GROUP                     | 触发器组       | nvarchar                                                  | 150                                                        | not null           | 主键     |        |
| JOB_NAME                          | 作业名称       | nvarchar                                                  | 150                                                        | not null           | 外键     |        |
| JOB_GROUP                         | 作业组         | nvarchar                                                  | 150                                                        | not null           | 外键     |        |
| DESCRIPTION                       | 描述           | nvarchar                                                  | 250                                                        | null               |          |        |
| NEXT_FIRE_TIME                    | 下次执行时间   | bigint                                                    |                                                            | null               |          |        |
| PREV_FIRE_TIME                    | 前一次执行时间 | bigint                                                    |                                                            | null               |          |        |
| PRIORITY                          | 优先权         | int                                                       |                                                            | null               |          |        |
| TRIGGER_STATE                     | 触发器状态     | nvarchar                                                  | 16                                                         | not null           |          |        |
| TRIGGER_TYPE                      | 触发器类型     | nvarchar                                                  | 8                                                          | not null           |          |        |
| START_TIME                        | 开始时间       | bigint                                                    |                                                            | not null           |          |        |
| END_TIME                          | 结束时间       | bigint                                                    |                                                            | null               |          |        |
| CALENDAR_NAME                     | 日历名称       | nvarchar                                                  | 200                                                        | null               |          |        |
| MISFIRE_INSTR                     | 失败次数       | int                                                       |                                                            | null               |          |        |
| JOB_DATA]                         | 作业数据       | image                                                     |                                                            | null               |          |        |
| **约束**                          | **约束名**     | **约束类型**                                              | **约束字段**                                               | **其它表约束字段** |          |        |
| PRIMARY KEY CLUSTERED             | 主键           | [SCHED_NAME] ASC, [TRIGGER_NAME] ASC, [TRIGGER_GROUP] ASC |                                                            |                    |          |        |
| FK_QRTZ_TRIGGERS_QRTZ_JOB_DETAILS | 外键           | [SCHED_NAME], [JOB_NAME], [JOB_GROUP]                     | [QRTZ_JOB_DETAILS] ([SCHED_NAME], [JOB_NAME], [JOB_GROUP]) |                    |          |        |