package com.hyp.learn.data.dao;


import com.hyp.learn.data.entity.Department;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.dao
 * hyp create at 19-12-26
 **/

public interface DepartmentMapper {

    public Department getDeptById(Integer id);

    public Department getDeptByIdStep(Integer id);
}
