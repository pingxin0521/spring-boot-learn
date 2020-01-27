package com.hyp.learn.mongodb.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mongodb.bean
 * hyp create at 20-1-24
 **/
@Data
@Accessors(chain = true)
@Document(collection = "t_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    private Long id;

    private String username;

    private String password;

}
