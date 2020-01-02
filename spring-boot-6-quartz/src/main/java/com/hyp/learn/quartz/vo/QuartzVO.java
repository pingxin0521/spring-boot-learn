package com.hyp.learn.quartz.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.entity
 * hyp create at 19-12-31
 **/
@Data
public class QuartzVO {
    private String jobName;//任务名称
    private String jobGroup;//任务分组
    private String description;//任务描述
    private String jobClassName;//执行类
    private String cronExpression;//执行时间

    private String oldJobName;//任务名称 用于修改
    private String oldJobGroup;//任务分组 用于修改

    private Integer pageNum;
    private Integer pageSize;

}
