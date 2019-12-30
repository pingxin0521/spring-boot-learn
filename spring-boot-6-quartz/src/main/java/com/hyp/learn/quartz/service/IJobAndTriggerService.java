package com.hyp.learn.quartz.service;

import com.github.pagehelper.PageInfo;
import com.hyp.learn.quartz.entity.JobAndTrigger;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.service
 * hyp create at 19-12-29
 **/
public interface IJobAndTriggerService {
    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
