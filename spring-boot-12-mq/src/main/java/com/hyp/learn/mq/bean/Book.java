package com.hyp.learn.mq.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mq.bean
 * hyp create at 20-1-3
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String name;
    private String author;
}
