package com.hyp.learn.jpa.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.jpa.entity
 * hyp create at 20-1-2
 **/
@Data
//声明为实体类
@Entity
//配置表的属性，默认表名为实体类的名称单词首字符小写
@Table(name = "tb_user")
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {
    @Id
    //自增主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String lastName;

    @Column
    private String email;
}
