package com.hyp.learn.quartz.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.entity
 * hyp create at 19-12-31
 **/
@Data
public class JobEntity implements Serializable {
  private String jobName;
  private String jobGroupName;
  private String description;
  private String jobStatus;
  private String jobTime;
}
