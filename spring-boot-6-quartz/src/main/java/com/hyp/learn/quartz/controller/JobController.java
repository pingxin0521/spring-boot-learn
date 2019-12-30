package com.hyp.learn.quartz.controller;

import com.github.pagehelper.PageInfo;
import com.hyp.learn.quartz.entity.JobAndTrigger;
import com.hyp.learn.quartz.service.IJobAndTriggerService;
import com.hyp.learn.quartz.service.impl.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.controller
 * hyp create at 19-12-29
 **/
@RestController
@RequestMapping(value = "/job")
@Slf4j
public class JobController {
    @Autowired
    private IJobAndTriggerService iJobAndTriggerService;

    @Autowired
    private QuartzService quartzService;

    //加入Qulifier注解，通过名称注入bean
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;


    /**
     * 在添加新的任务的时候，填写任务名称时一定要把这个Job类的完整路径输入进来
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    @PostMapping(value = "/addjob")
    public void addjob(@RequestParam(value = "jobClassName") String jobClassName,
                       @RequestParam(value = "jobGroupName") String jobGroupName,
                       @RequestParam(value = "cronExpression") String cronExpression,
                       @RequestParam(value = "notes", required = false) String notes) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("jobClassName", jobClassName);
        map.put("jobGroupName", jobGroupName);
        map.put("cronExpression", cronExpression);
        map.put("notes", notes);
        quartzService.addJob(getClass(jobClassName).getClass(), jobClassName, jobGroupName, cronExpression, map);
    }


    @PostMapping(value = "/pausejob")
    public void pausejob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        quartzService.pauseJob(jobClassName, jobGroupName);
    }


    @PostMapping(value = "/resumejob")
    public void resumejob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        quartzService.resumeJob(jobClassName,jobGroupName);
    }


    @PostMapping(value = "/reschedulejob")
    public void rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName,
                              @RequestParam(value = "jobGroupName") String jobGroupName,
                              @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        quartzService.updateJob(jobClassName, jobGroupName, cronExpression);
    }

    @PostMapping(value = "/deletejob")
    public void deletejob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        quartzService.deleteJob(jobClassName, jobGroupName);
    }



    @GetMapping(value = "/queryjob")
    public Map<String, Object> queryjob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {

        PageInfo<JobAndTrigger> jobAndTrigger = iJobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        return map;
    }

    public static QuartzJobBean getClass(String classname) throws Exception {
        Class<?> clz = Class.forName(classname);
        return (QuartzJobBean) clz.newInstance();
    }


}
