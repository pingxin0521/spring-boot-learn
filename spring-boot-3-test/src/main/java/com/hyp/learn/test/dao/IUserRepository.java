package com.hyp.learn.test.dao;

import com.hyp.learn.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<User,Long>{
    boolean updateUser(User user);

    void addUser(User user);
}
