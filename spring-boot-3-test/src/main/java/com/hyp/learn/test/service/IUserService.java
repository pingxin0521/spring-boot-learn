package com.hyp.learn.test.service;

import com.hyp.learn.test.model.User;

public interface IUserService {

    User findOne(Long id);

    boolean updateUsername(Long id, String username);

}
