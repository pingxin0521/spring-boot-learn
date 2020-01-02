package com.hyp.learn.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.lang.NonNullApi;

import java.io.Serializable;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.pojo
 * hyp create at 19-12-26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("Employee")
public class Employee implements Serializable {

    private Integer id;
    private String lastName;
    private String email;
    private String gender;
}