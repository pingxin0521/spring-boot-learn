package com.hyp.learn.mongodb.dao;

import com.hyp.learn.mongodb.bean.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mongodb.dao
 * hyp create at 20-1-24
 **/
public interface UserRepository extends MongoRepository<UserInfo,Long> {
}
