package com.hyp.learn.quartz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyp.learn.quartz.entity.QuartzEntity;
import com.hyp.learn.quartz.service.IJobAndTriggerService;
import com.hyp.learn.quartz.service.impl.QuartzService;
import com.hyp.learn.quartz.vo.QuartzVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.controller
 * hyp create at 19-12-29
 **/
@RestController
@RequestMapping(value = "/job")
@Slf4j
@Api("任务管理")
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
     */
    @PostMapping(value = {"/add"})
    public void addjob(@RequestBody QuartzVO quartz) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", quartz);
        //如果是修改  展示旧的 任务
        if (quartz.getOldJobGroup() != null) {
            quartzService.deleteJob(quartz);
        }
        quartzService.addJob(quartz, map);
    }

    @PostMapping("/trigger")
    public void trigger(@RequestBody QuartzVO quartz) throws SchedulerException {
        quartzService.runAJobNow(quartz);
    }


    @PostMapping(value = {"/pause"})
    public void pausejob(@RequestBody QuartzVO quartz) throws Exception {
        quartzService.pauseJob(quartz);
    }


    @PostMapping(value = {"/resume"})
    public void resumejob(@RequestBody QuartzVO quartz) throws Exception {
        quartzService.resumeJob(quartz);
    }


    @PostMapping(value = "/reschedule")
    public void rescheduleJob(@RequestBody QuartzVO quartz) throws Exception {
        quartzService.updateJob(quartz);
    }

    @PostMapping(value = {"/remove"})
    public void deletejob(@RequestBody QuartzVO quartz) throws Exception {
        quartzService.deleteJob(quartz);
    }


    @PostMapping(value = { "/list"})
    public PageInfo<QuartzEntity> queryjob(@RequestBody QuartzVO quartz) {
        PageInfo<QuartzEntity> details = iJobAndTriggerService.getJobAndTriggerDetails(quartz);
        return details;
    }


    public static QuartzJobBean getClass(String classname) throws Exception {
        Class<?> clz = Class.forName(classname);
        return (QuartzJobBean) clz.newInstance();
    }


}
