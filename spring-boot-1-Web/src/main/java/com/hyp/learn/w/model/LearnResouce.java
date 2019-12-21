package com.hyp.learn.w.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.w.model
 * hyp create at 19-12-21
 **/
@Data
@AllArgsConstructor
public class LearnResouce {
    private String author;
    private String title;
    private String url;
}
