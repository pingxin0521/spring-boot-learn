package com.hyp.learn.shiro.service;

import com.hyp.learn.shiro.bean.UserInfo;
import com.hyp.learn.shiro.dao.UserInfoDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.service
 * hyp create at 20-1-26
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}
