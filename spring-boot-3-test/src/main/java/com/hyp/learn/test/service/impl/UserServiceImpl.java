package com.hyp.learn.test.service.impl;

import com.hyp.learn.test.dao.IUserRepository;
import com.hyp.learn.test.model.User;
import com.hyp.learn.test.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;


    @Override
    public User findOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public boolean updateUsername(Long id, String username) {
        User user = findOne(id);
        if(user == null) {
            return false;
        }
        user.setUsername(username);
        return userRepository.updateUser(user);
    }

}
