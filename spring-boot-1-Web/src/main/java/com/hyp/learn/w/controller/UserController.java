package com.hyp.learn.w.controller;

import com.hyp.learn.w.dto.UserAddDTO;
import com.hyp.learn.w.dto.UserUpdateDTO;
import com.hyp.learn.w.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "用户 API 接口")
public class UserController {

    @RequestMapping(name = "/getUser", method = RequestMethod.POST)
    public UserVO getUser() {
        UserVO userVO = new UserVO();
        userVO.setName("小明");
        userVO.setAge(12);
        userVO.setPass("123456");
        return userVO;
    }

    @RequestMapping(value = "/getUsers",method = RequestMethod.GET)
    public List<UserVO> getUsers() {
        List<UserVO> userVOS = new ArrayList<UserVO>();
        UserVO userVO1 = new UserVO();
        userVO1.setName("neo");
        userVO1.setAge(30);
        userVO1.setPass("neo123");
        userVOS.add(userVO1);
        UserVO userVO2 = new UserVO();
        userVO2.setName("小明");
        userVO2.setAge(12);
        userVO2.setPass("123456");
        userVOS.add(userVO2);
        return userVOS;
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询用户列表", notes = "目前仅仅是作为测试，所以返回用户全列表")
    public List<UserVO> list() {
        // 查询列表
        List<UserVO> result = new ArrayList<>();
        result.add(new UserVO().setId(1).setName("yudaoyuanma"));
        result.add(new UserVO().setId(2).setName("woshiyutou"));
        result.add(new UserVO().setId(3).setName("chifanshuijiao"));
        // 返回列表
        return result;
    }

    @GetMapping("/get")
    @ApiOperation("获得指定用户编号的用户")
    @ApiImplicitParam(name = "id", value = "用户编号", paramType = "query", dataTypeClass = Integer.class, required = true, example = "1024")
    public UserVO get(@RequestParam("id") Integer id) {
        // 查询并返回用户
        return new UserVO().setId(id).setName(UUID.randomUUID().toString());
    }

    @PostMapping("add")
    @ApiOperation("添加用户")
    public Integer add(UserAddDTO addDTO) {
        // 插入用户记录，返回编号
        Integer returnId = UUID.randomUUID().hashCode();
        // 返回用户编号
        return returnId;
    }

    @PostMapping("/update")
    @ApiOperation("更新指定用户编号的用户")
    public Boolean update(UserUpdateDTO updateDTO) {
        // 更新用户记录
        Boolean success = true;
        // 返回更新是否成功
        return success;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除指定用户编号的用户")
    @ApiImplicitParam(name = "id", value = "用户编号", paramType = "query", dataTypeClass = Integer.class, required = true, example = "1024")
    public Boolean delete(@RequestParam("id") Integer id) {
        // 删除用户记录
        Boolean success = false;
        // 返回是否更新成功
        return success;
    }
}