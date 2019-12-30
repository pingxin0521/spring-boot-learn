package com.hyp.learn.quartz.job;

import lombok.extern.slf4j.Slf4j;
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
    public void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        log.error("Hello Job执行时间: " + new Date());

    }
}
