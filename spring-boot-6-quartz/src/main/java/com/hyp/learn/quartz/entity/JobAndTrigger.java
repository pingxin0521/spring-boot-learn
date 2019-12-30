package com.hyp.learn.quartz.entity;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.entity
 * hyp create at 19-12-29
 **/

import lombok.Data;

import java.math.BigInteger;

@Data
public class JobAndTrigger {

    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String triggerName;
    private String triggerGroup;
    private BigInteger repeatInterval;
    private BigInteger timesTriggered;
    private String cronExpression;
    private String timeZoneId;


}
