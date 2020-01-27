package com.hyp.learn.shiro.dao;

import com.hyp.learn.shiro.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.dao
 * hyp create at 20-1-26
 **/
public interface UserInfoDao extends JpaRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}