package com.hyp.learn.mongodb;

import com.hyp.learn.mongodb.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setId(1);
        user.setName("平心");
        user.setSex("男");
        mongoTemplate.insert(user);
        user.setId(2);
        user.setName("李白");
        user.setSex("男");
        mongoTemplate.insert(user);
        System.out.println(("插入成功！"));
    }

    @Test
    public void find() throws Exception {
        List<User> users = mongoTemplate.findAll(User.class);
        users.forEach(u -> {
            System.out.println((u.toString()));
        });

        User user = mongoTemplate.findById(1, User.class);
        System.out.println((user));
    }

    @Test
    public void update() {
        mongoTemplate.updateFirst(query(where("name").is("平心")), Update.update("name", "我是平心"), User.class);
    }

    @Test
    public void delete(){
        User user=new User();
        user.setId(2);
        mongoTemplate.remove(user);
    }


}
