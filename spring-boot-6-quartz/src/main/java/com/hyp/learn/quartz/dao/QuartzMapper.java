package com.hyp.learn.quartz.dao;

import com.hyp.learn.quartz.entity.QuartzEntity;

import java.util.List;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.dao
 * hyp create at 19-12-29
 **/
public interface QuartzMapper {
    public List<QuartzEntity> getJobAndTriggerDetails();
}
