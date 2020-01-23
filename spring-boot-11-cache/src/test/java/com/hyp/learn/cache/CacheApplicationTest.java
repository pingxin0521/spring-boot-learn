package com.hyp.learn.cache;

import com.hyp.learn.cache.dao.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.cache
 * hyp create at 20-1-3
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class CacheApplicationTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void test()
    {
        System.out.println(employeeMapper.getEmpById(1));
    }
}