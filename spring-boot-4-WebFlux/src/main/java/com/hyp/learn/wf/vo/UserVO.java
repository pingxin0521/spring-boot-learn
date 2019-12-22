package com.hyp.learn.wf.vo;

/**
 * @author hyp
 * Project username is spring-boot-learn
 * Include in com.hyp.learn.w.model
 * hyp create at 19-12-21
 **/
public class UserVO {
    /**
     * 编号
     */
    private Integer id;
    private String username;
    private int age;
    private String pass;
    //setter、getter省略

    public String getUsername() {
        return username;
    }

    public UserVO setUsername(String username) {
        this.username = username;
        return this;
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

    public Integer getId() {
        return id;
    }

    public UserVO setId(Integer id) {
        this.id = id;
        return this;
    }


}
