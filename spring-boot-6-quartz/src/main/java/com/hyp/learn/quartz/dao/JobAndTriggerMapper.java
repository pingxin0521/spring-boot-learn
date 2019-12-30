package com.hyp.learn.quartz.dao;

import com.hyp.learn.quartz.entity.JobAndTrigger;

import java.util.List;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.dao
 * hyp create at 19-12-29
 **/
public interface JobAndTriggerMapper {
    public List<JobAndTrigger> getJobAndTriggerDetails();
}
