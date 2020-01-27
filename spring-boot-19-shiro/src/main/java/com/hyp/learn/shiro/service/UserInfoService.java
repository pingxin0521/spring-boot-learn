package com.hyp.learn.shiro.service;

import com.hyp.learn.shiro.bean.UserInfo;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.service
 * hyp create at 20-1-26
 **/
public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}
