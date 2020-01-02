package com.hyp.learn.quartz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyp.learn.quartz.dao.QuartzMapper;
import com.hyp.learn.quartz.entity.QuartzEntity;
import com.hyp.learn.quartz.service.IJobAndTriggerService;
import com.hyp.learn.quartz.vo.QuartzVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.quartz.service.impl
 * hyp create at 19-12-29
 **/

@Service
public class JobAndTriggerImpl implements IJobAndTriggerService {

    @Autowired
    private QuartzMapper quartzMapper;

    @Override
    public PageInfo<QuartzEntity> getJobAndTriggerDetails(QuartzVO quartz) {
        PageHelper.startPage(quartz.getPageNum(), quartz.getPageSize());
        List<QuartzEntity> list = quartzMapper.getJobAndTriggerDetails();
        PageInfo<QuartzEntity> page = new PageInfo<QuartzEntity>(list);
        return page;
    }

}
