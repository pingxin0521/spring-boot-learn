package com.hyp.learn.cache.controller;

import com.hyp.learn.cache.entity.Employee;
import com.hyp.learn.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.cache.controller
 * hyp create at 20-1-3
 **/
@RestController
@RequestMapping("/emp")
public class EmploeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        return employeeService.getEmp(id);
    }

    @PostMapping("/")
    public Employee update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return employee;
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable Integer id) {
        employeeService.delete(id);
        return "删除成功";
    }


    @GetMapping("/name")
    public Employee getEmpByName(String name) {
        return employeeService.getEmpByName(name);
    }
}
