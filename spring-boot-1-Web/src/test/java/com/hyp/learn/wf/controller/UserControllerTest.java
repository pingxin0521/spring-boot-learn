//package com.hyp.learn.wf.controller;
//
//import com.hyp.learn.w.controller.UserController;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
///**
// * @author hyp
// * Project name is spring-boot-learn
// * Include in com.hyp.learn.w.controller
// * hyp create at 19-12-21
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserControllerTest {
//
//    private MockMvc mvc;
//
//    @Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
//    }
//
//    @Test
//    public void getUser() throws Exception {
//
//        String responseString = mvc.perform(MockMvcRequestBuilders.post("/getUser"))
//                .andReturn().getResponse().getContentAsString();
//        System.out.println("result : " + responseString);
//    }
//
//    @Test
//    public void getUsers() throws Exception {
//        String responseString = mvc.perform(MockMvcRequestBuilders.get("/getUsers"))
//                .andReturn().getResponse().getContentAsString();
//        System.out.println("result : "+responseString);
//    }
//}