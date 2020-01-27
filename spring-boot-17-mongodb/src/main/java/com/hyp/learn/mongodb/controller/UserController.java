package com.hyp.learn.mongodb.controller;

import com.hyp.learn.mongodb.bean.UserInfo;
import com.hyp.learn.mongodb.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mongodb.controller
 * hyp create at 20-1-24
 **/
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("save")
    public String save() {
        UserInfo userInfo = new UserInfo(System.currentTimeMillis(), "用户" + System.currentTimeMillis(), "123");
        userRepository.save(userInfo);
        return "success";
    }


    @GetMapping("getUserList")
    public List<UserInfo> getUserList() {
        List<UserInfo> userInfoList = userRepository.findAll();
        return userInfoList;
    }

    @GetMapping("delete")
    public String delete(Long id) {
        userRepository.deleteById(id);
        return "success";
    }

    @GetMapping("update")
    public String update(Long id, String username, String password) {
        UserInfo userInfo = new UserInfo(id, username, password);
        userRepository.save(userInfo);
        return "success";
    }
}
