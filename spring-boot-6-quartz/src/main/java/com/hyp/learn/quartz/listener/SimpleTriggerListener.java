package com.hyp.learn.quartz.listener;

import com.hyp.learn.quartz.commons.RedisDistributedLock;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 修改QuartzConfig,注册自己的triggerListener
 *1.getName方法的返回值需要自己定义，默认为null，否则会抛出异常。
 *
 * 2.vetoJobExecution方法在任务调起时执行，当返回false时，继续执行任务，否则停止任务。
 *
 * 3.在vetoJobExecution方法中获取到锁，则继续执行任务，没有获取到则停止。
 *
 * 4.triggerComplete方法在在任务调度执行完成时，调用此方法来释放锁。

 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.listener
 * hyp create at 19-12-31
 **/
@Component
public class SimpleTriggerListener implements TriggerListener {

    private Logger logger = LoggerFactory.getLogger(SimpleTriggerListener.class);

    @Autowired
    private RedisDistributedLock distributedLock;

    @Override
    public String getName() {
        return "SimpleTriggerListener";
    }


    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
    }

    /**
     * 在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法。
     * 假如这个方法返回 true，这个 Job 将不会为此次 Trigger 触发而得到执行。
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        logger.info("=====》 trigger开始执行,开始获取redis锁");
        JobKey jobKey = trigger.getJobKey();
        String lockKey = jobKey.getGroup() + ":" + jobKey.getName();
        boolean flag = distributedLock.lock(lockKey);
        if (!flag) {
            //获取到锁了
            logger.warn("没获取到锁，本次调度结束");
            return true;
        }
        logger.info("获取到锁了，继续往下执行");
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        logger.info("调度执行完毕，开始释放锁");
        JobKey jobKey = trigger.getJobKey();
        String lockKey = jobKey.getGroup() + ":" + jobKey.getName();
        distributedLock.unLock(lockKey);
        logger.error("本次作业执行结束==============》");
    }
}
