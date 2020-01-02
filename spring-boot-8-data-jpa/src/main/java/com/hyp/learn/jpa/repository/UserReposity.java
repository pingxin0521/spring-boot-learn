package com.hyp.learn.jpa.repository;

import com.hyp.learn.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.jpa.repository
 * hyp create at 20-1-2
 **/
//PagingAndSortingRepository和CrudRepository,<实体类，主键类型>
public interface UserReposity extends JpaRepository<User,Integer> {
    //按照特定名称的方法声明可以自动生成代理方法
    public User findFirstByEmail(String email);
}
