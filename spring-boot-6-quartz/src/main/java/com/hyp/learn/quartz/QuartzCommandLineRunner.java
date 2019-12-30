package com.hyp.learn.quartz;

import com.hyp.learn.quartz.job.InternalJob;
import com.hyp.learn.quartz.service.impl.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;

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
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", 1);
        quartzService.deleteJob("job", "test");
        quartzService.addJob(InternalJob.class, "job", "test", "0 * * * * ?", map);

        map.put("name", 2);
        quartzService.deleteJob("job2", "test");
        quartzService.addJob(InternalJob.class, "job2", "test", "10 * * * * ?", map);

        map.put("name", 3);
        quartzService.deleteJob("job3", "test2");
        quartzService.addJob(InternalJob.class, "job3", "test2", "15 * * * * ?", map);

        map.put("name", 13);
        quartzService.deleteJob("job4", "test2");
        quartzService.addJob(InternalJob.class, "job4", "test2", "15 * * * * ?", map);
        map.put("name", 14);
        quartzService.deleteJob("job5", "test2");
        quartzService.addJob(InternalJob.class, "job5", "test2", "15 * * * * ?", map);

    }
}
