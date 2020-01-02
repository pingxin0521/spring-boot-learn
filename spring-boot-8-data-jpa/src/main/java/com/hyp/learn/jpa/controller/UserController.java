package com.hyp.learn.jpa.controller;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.hyp.learn.jpa.entity.User;
import com.hyp.learn.jpa.repository.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.jpa.controller
 * hyp create at 20-1-2
 **/
@RestController
public class UserController {
    @Autowired
    private UserReposity userReposity;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        Optional<User> user = userReposity.findById(id);
        return user.orElse(null);
    }

    @PostMapping("/user")
    public User insertUser(@RequestBody  User user){


        return userReposity.save(user);
    }

}
