package com.hyp.learn.cache.dao;

import com.hyp.learn.cache.entity.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.dao
 * hyp create at 19-12-26
 **/
@Mapper
public interface EmployeeMapper {


    @Select("select * from tbl_employee where id = #{id}")
    public Employee getEmpById(Integer id);

    @Insert("Insert into tbl_employee(last_name,email,gender,d_id) Values(#{lastName},#{email},#{gender},#{dId}) ")
    public Long addEmp(Employee employee);

    @Update("Update tbl_employee set last_name=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
    public boolean updateEmp(Employee employee);

    @Delete("delete tbl_employee where id =#{id}")
    public boolean deleteEmpById(Integer id);

    @Select("select * from tbl_employee where last_name like #{name} limit 0,1")
    public Employee getEmpByName(String name);
}
