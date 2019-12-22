package com.hyp.learn.wf.controller;


import com.hyp.learn.wf.dto.UserAddDTO;
import com.hyp.learn.wf.dto.UserUpdateDTO;
import com.hyp.learn.wf.service.impl.UserService;
import com.hyp.learn.wf.vo.UserVO;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * GET	/users	查询用户列表
 * GET	/users/{id}	获得指定用户编号的用户
 * POST	/users	添加用户
 * PUT	/users/{id}	更新指定用户编号的用户
 * DELETE	/users/{id}	删除指定用户编号的用户
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.w.controller
 * hyp create at 19-12-21
 **/
@RestController
@RequestMapping("/users")
public class UserController {


    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    @GetMapping("/list")
    public Flux<UserVO> list() {
        // 查询列表
        List<UserVO> result = new ArrayList<>();
        result.add(new UserVO().setId(1).setUsername("yudaoyuanma"));
        result.add(new UserVO().setId(2).setUsername("woshiyutou"));
        result.add(new UserVO().setId(3).setUsername("chifanshuijiao"));
        // 返回列表
        return Flux.fromIterable(result);
    }

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/get")
    public Mono<UserVO> get(@RequestParam("id") Integer id) {
        // 查询用户
        UserVO user = new UserVO().setId(id).setUsername("username:" + id);
        // 返回
        return Mono.just(user);
    }
    @Autowired
    private UserService userService;

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/v2/get")
    public Mono<UserVO> get2(@RequestParam("id") Integer id) {
        // 查询用户
        UserVO user = userService.get(id);
        // 返回
        return Mono.just(user);
    }

    /**
     * 添加用户
     *
     * @param addDTO 添加用户信息 DTO
     * @return 添加成功的用户编号
     */
    @PostMapping("/add")
    public Mono<Integer> add(@RequestBody Publisher<UserAddDTO> addDTO) {
        // 插入用户记录，返回编号
        Integer returnId = 1;
        // 返回用户编号
        return Mono.just(returnId);
    }

    // UserController.java

    /**
     * 添加用户
     *
     * @param addDTO 添加用户信息 DTO
     * @return 添加成功的用户编号
     */
    @PostMapping("/add2")
    public Mono<Integer> add(Mono<UserAddDTO> addDTO) {
        // 插入用户记录，返回编号
        Integer returnId = UUID.randomUUID().hashCode();
        // 返回用户编号
        return Mono.just(returnId);
    }

    /**
     * 更新指定用户编号的用户
     *
     * @param updateDTO 更新用户信息 DTO
     * @return 是否修改成功
     */
    @PostMapping("/update")
    public Mono<Boolean> update(@RequestBody Publisher<UserUpdateDTO> updateDTO) {
        // 更新用户记录
        Boolean success = true;
        // 返回更新是否成功
        return Mono.just(success);
    }

    /**
     * 删除指定用户编号的用户
     *
     * @param id 用户编号
     * @return 是否删除成功
     */
    @PostMapping("/delete") // URL 修改成 /delete ，RequestMethod 改成 DELETE
    public Mono<Boolean> delete(@RequestParam("id") Integer id) {
        // 删除用户记录
        Boolean success = false;
        // 返回是否更新成功
        return Mono.just(success);
    }

}
