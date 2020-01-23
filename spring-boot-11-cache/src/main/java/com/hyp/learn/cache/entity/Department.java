package com.hyp.learn.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.pojo
 * hyp create at 19-12-26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("Department")
public class Department implements Serializable {

    private Integer id;
    private String departmentName;
    private List<Employee> emps;

}
