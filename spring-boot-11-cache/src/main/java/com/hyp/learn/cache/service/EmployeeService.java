package com.hyp.learn.cache.service;

import com.hyp.learn.cache.dao.EmployeeMapper;
import com.hyp.learn.cache.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.cache.service
 * hyp create at 20-1-3
 **/
@Service
@CacheConfig(cacheNames = "px_employee")
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再要相同的调用参数，直接从缓存中取出，不用调用方法
     * <p>
     * 1.cacheName/value：指定cache组件的名称，可以指定单/多个：
     * key：要按SpEL表达式去写，
     *
     * @param id
     * @return
     */
//    @Cacheable(cacheNames = "px_employee",  keyGenerator = "myKeyGenerator",condition = "#a0>1", unless = "#result == null")
    @Cacheable(cacheNames = "px_employee")
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "员工");
        return employeeMapper.getEmpById(id);
    }

    /**
     * @param employee
     * @return
     * @CachePut:及调用方法，有更新缓存数据 先调用目标方法，再更新缓存
     */
    @CachePut(cacheNames = "px_employee", key = "#result.id")
    public Employee update(Employee employee) {

        System.out.println("update" + employee + "员工");

        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * 清除操作默认是在对应方法成功执行之后触发的
     *
     * @param id
     */
    @CacheEvict(cacheNames = "px_employee", beforeInvocation = true)
    public void delete(Integer id) {
        System.out.println("删除" + id + "员工");
        employeeMapper.deleteEmpById(id);
    }

    @Caching(
            cacheable = {
                    @Cacheable(cacheNames = "px_employee", key = "#a0")
            },
            put = {
                    @CachePut(cacheNames = "px_employee", key = "#result.id"),
                    @CachePut(cacheNames = "px_employee", key = "#result.email")
            }
    )
    public Employee getEmpByName(String name) {
        return employeeMapper.getEmpByName(name);
    }
}
