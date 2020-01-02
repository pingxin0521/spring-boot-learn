package com.hyp.learn.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.job
 * hyp create at 19-12-29
 **/
@Slf4j
public class HelloJob extends QuartzJobBean {


    public HelloJob() {

    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // 获取参数
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        // 业务逻辑 ...
        System.out.println("大吉大利、今晚吃鸡-01-测试集群模式");

        System.out.println("Hello!  HelloJob is executing."+new Date() );
        //取得job详情
        JobDetail jobDetail = context.getJobDetail();
        // 取得job名称
        String jobName = jobDetail.getClass().getName();
        log.info("Name: " + jobDetail.getClass().getSimpleName());
        //取得job的类
        log.info("Job Class: " + jobDetail.getJobClass());
        //取得job开始时间
        log.info(jobName + " fired at " + context.getFireTime());
        //取得job下次触发时间
        log.info("Next fire time " + context.getNextFireTime());

        JobDataMap dataMap =  jobDetail.getJobDataMap();

        log.info("itstyle: " + dataMap.getString("itstyle"));
        log.info("blog: " + dataMap.getString("blog"));
        String[] array = (String[]) dataMap.get("data");
        for(String str:array){
            log.info("data: " + str);
        }
    }
}
