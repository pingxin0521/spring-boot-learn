package com.hyp.learn.w.model;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.w.model
 * hyp create at 19-12-21
 **/
public class User {
    private String name;
    private int age;
    private String pass;
    //setter、getter省略

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
