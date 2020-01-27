package com.hyp.learn.mongodb.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mongodb.bean
 * hyp create at 20-1-24
 **/
@Data
public class User {

    @Id
    private int id;
    private String name;
    private String sex;

}