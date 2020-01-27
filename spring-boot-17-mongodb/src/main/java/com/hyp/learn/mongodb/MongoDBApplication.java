package com.hyp.learn.mongodb;

import com.hyp.learn.mongodb.utils.MongoTemplateHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.qs
 * hyp create at 19-12-21
 **/
@SpringBootApplication
public class MongoDBApplication {


    public static void main(String[] args) {

        // 启动Sprign Boot
        ConfigurableApplicationContext ctx = SpringApplication.run(MongoDBApplication.class);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    private static final String COLLECTION_NAME = "personal_info";


    public MongoDBApplication(MongoTemplateHelper mongoTemplateHelper) {
        Map<String, Object> records = new HashMap<>(4);
        records.put("name", "平心Blog");
        records.put("github", "hanyunpeng0521.github.io");
        records.put("time", LocalDateTime.now());

        mongoTemplateHelper.saveRecord(records, COLLECTION_NAME);

        Map<String, Object> query = new HashMap<>(4);
        query.put("name", "平心Blog");
        mongoTemplateHelper.queryRecord(query, COLLECTION_NAME);
    }
}
