package com.hyp.learn.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.data.controller
 * hyp create at 20-1-1
 **/
@RestController
@RequestMapping("/main")
public class MainController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/query")
    public List query() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tb_user");
        list.forEach(System.out::println);
        return list;
    }
}
