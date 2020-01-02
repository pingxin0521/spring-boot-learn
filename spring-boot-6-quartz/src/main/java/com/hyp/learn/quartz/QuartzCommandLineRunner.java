package com.hyp.learn.quartz;

import com.hyp.learn.quartz.service.impl.QuartzService;
import com.hyp.learn.quartz.vo.QuartzVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz
 * hyp create at 19-12-29
 **/
@Component
@Slf4j
public class QuartzCommandLineRunner implements CommandLineRunner {

    @Autowired
    QuartzService quartzService;


    @Override
    public void run(String... args) throws Exception {

        Map<String, Object> data = new HashMap<>();

        data.put("itstyle", "平心欢迎你");
        data.put("blog", "https://pingxin0521.coding.me/");
        data.put("data", new String[]{"张三", "李四"});

        QuartzVO quartz = new QuartzVO();
        quartz.setJobName("test01");
        quartz.setJobGroup("test");
        quartz.setDescription("测试任务");
        quartz.setJobClassName("com.hyp.learn.quartz.job.InternalJob");
        quartz.setCronExpression("0 * * * * ?");
        quartzService.addJob(quartz, data);


        quartz.setJobName("test02");
        quartz.setJobGroup("test");
        quartz.setCronExpression("5 */5 * * * ?");
        quartzService.addJob(quartz, data);

        quartz.setJobName("test03");
        quartz.setJobGroup("test1");
        quartz.setJobClassName("com.hyp.learn.quartz.job.HelloJob");
        quartz.setCronExpression("25 */5 * * * ?");
        quartzService.addJob(quartz, data);


    }
}
