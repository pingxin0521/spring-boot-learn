package com.hyp.learn.quartz.service.impl;

import com.hyp.learn.quartz.entity.JobEntity;
import com.hyp.learn.quartz.entity.QuartzEntity;
import com.hyp.learn.quartz.vo.QuartzVO;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.service.impl
 * hyp create at 19-12-29
 **/
@Service
public class QuartzService {

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @PostConstruct
    public void startScheduler() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void endScheduler() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass     任务实现类
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     * @param jobTime      时间表达式 (这是每隔多少秒为一次任务)
     * @param jobTimes     运行的次数 （<0:表示不限次数）
     * @param jobData      参数
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, int jobTime,
                       int jobTimes, Map jobData) throws SchedulerException {

        // 任务名称和组构成任务key
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName)
                .build();
        // 设置job参数
        if (jobData != null && jobData.size() > 0) {
            jobDetail.getJobDataMap().putAll(jobData);
        }
        // 使用simpleTrigger规则
        Trigger trigger = null;
        if (jobTimes < 0) {
            trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withIntervalInSeconds(jobTime))
                    .startNow().build();
        } else {
            trigger = TriggerBuilder
                    .newTrigger().withIdentity(jobName, jobGroupName).withSchedule(SimpleScheduleBuilder
                            .repeatSecondlyForever(1).withIntervalInSeconds(jobTime).withRepeatCount(jobTimes))
                    .startNow().build();
        }
        scheduler.scheduleJob(jobDetail, trigger);


    }

    public static QuartzJobBean getClass(String classname) throws Exception {
        Class<?> clz = Class.forName(classname);
        return (QuartzJobBean) clz.newInstance();
    }


    /**
     * 增加一个job
     * <p>
     * //     * @param jobClass     任务实现类
     * //     * @param jobName      任务名称(建议唯一)
     * //     * @param jobGroupName 任务组名
     * //     * @param jobTime      时间表达式 （如：0/5 * * * * ? ）
     * //     * @param jobData      参数
     */
    public void addJob(QuartzVO quartz, Map jobData) throws Exception {
        // 创建jobDetail实例，绑定Job实现类
        // 指明job的名称，所在组的名称，以及绑定job类
        // 任务名称和组构成任务key
        JobDetail jobDetail = JobBuilder
                .newJob(getClass(quartz.getJobClassName()).getClass())
                .withIdentity(quartz.getJobName(), quartz.getJobGroup())
                .withDescription(quartz.getDescription())
                .build();
        // 设置job参数
        if (jobData != null && jobData.size() > 0) {
            jobDetail.getJobDataMap().putAll(jobData);
        }
        // 定义调度触发规则
        // 使用cornTrigger规则
        // 触发器key
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(quartz.getJobName(), quartz.getJobGroup())
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(quartz.getCronExpression())).startNow().build();
        // 把作业和触发器注册到任务调度中
        scheduler.scheduleJob(jobDetail, trigger);


    }

    /**
     * 修改 一个job的 时间表达式
     * <p>
     * //     * @param jobName
     * //     * @param jobGroupName
     * //     * @param jobTime
     */
    public void updateJob(QuartzVO quartz) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(quartz.getCronExpression())).build();
        // 重启触发器
        scheduler.rescheduleJob(triggerKey, trigger);

    }

    /**
     * 删除任务一个job
     * <p>
     * //     * @param jobName      任务名称
     * //     * @param jobGroupName 任务组名
     */

    public void deleteJob(QuartzVO quartz) throws SchedulerException {
        scheduler.deleteJob(new JobKey(quartz.getJobName(), quartz.getJobGroup()));


    }

    /**
     * 暂停一个job
     * <p>
     * //     * @param jobName
     * //     * @param jobGroupName
     */
    public void pauseJob(QuartzVO quartz) throws SchedulerException {

        JobKey jobKey = JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.pauseJob(jobKey);


    }

    /**
     * 恢复一个job
     * <p>
     * //     * @param jobName
     * //     * @param jobGroupName
     */
    public void resumeJob(QuartzVO quartz) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.resumeJob(jobKey);

    }

    /**
     * 立即执行一个job
     * <p>
     * //     * @param jobName
     * //     * @param jobGroupName
     */
    public void runAJobNow(QuartzVO quartz) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.triggerJob(jobKey);

    }


    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    public List<JobEntity> queryAllJob() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        List<JobEntity> jobList = null;
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        jobList = new ArrayList<JobEntity>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                JobEntity jobEntity = new JobEntity();
                jobEntity.setJobName(jobKey.getName());
                jobEntity.setJobGroupName(jobKey.getGroup());
                jobEntity.setDescription("触发器:" + trigger.getKey());

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                jobEntity.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    jobEntity.setJobTime(cronExpression);
                }
                jobList.add(jobEntity);
            }
        }

        return jobList;
    }


    /**
     * 获取所有正在运行的job
     *
     * @return
     */
    public List<JobEntity> queryRunJob() throws SchedulerException {
        List<JobEntity> jobList = null;
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        jobList = new ArrayList<JobEntity>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            Map<String, Object> map = new HashMap<String, Object>();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            JobEntity jobEntity = new JobEntity();
            jobEntity.setJobName(jobKey.getName());
            jobEntity.setJobGroupName(jobKey.getGroup());
            jobEntity.setDescription("触发器:" + trigger.getKey());

            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            jobEntity.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                jobEntity.setJobTime(cronExpression);
            }
            jobList.add(jobEntity);
        }

        return jobList;
    }


}
