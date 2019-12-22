package com.hyp.learn.w.controller;

import com.hyp.learn.w.vo.UserVO;
import com.hyp.learn.w.dto.UserAddDTO;
import com.hyp.learn.w.dto.UserUpdateDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(name = "/getUser", method = RequestMethod.POST)
    public UserVO getUser() {
        UserVO userVO = new UserVO();
        userVO.setName("小明");
        userVO.setAge(12);
        userVO.setPass("123456");
        return userVO;
    }

    @RequestMapping("/getUsers")
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

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    @GetMapping("")
    public List<UserVO> list() {
        // 查询列表
        List<UserVO> result = new ArrayList<>();
        result.add(new UserVO().setId(1).setName("yudaoyuanma"));
        result.add(new UserVO().setId(2).setName("woshiyutou"));
        result.add(new UserVO().setId(3).setName("chifanshuijiao"));
        // 返回列表
        return result;
    }

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/{id}")
    public UserVO get(@PathVariable("id") Integer id) {
        // 查询并返回用户
        return new UserVO().setId(id).setName("username:" + id);
    }

    /**
     * 添加用户
     *
     * @param addDTO 添加用户信息 DTO
     * @return 添加成功的用户编号
     */
    @PostMapping("add")
    public Integer add(UserAddDTO addDTO) {
        // 插入用户记录，返回编号
        Integer returnId = 1;
        // 返回用户编号
        return returnId;
    }

    /**
     * 更新指定用户编号的用户
     *
     * @param id        用户编号
     * @param updateDTO 更新用户信息 DTO
     * @return 是否修改成功
     */
    @PutMapping("/{id}")
    public Boolean update(@PathVariable("id") Integer id, UserUpdateDTO updateDTO) {
        // 将 id 设置到 updateDTO 中
        updateDTO.setId(id);
        // 更新用户记录
        Boolean success = true;
        // 返回更新是否成功
        return success;
    }

    /**
     * 删除指定用户编号的用户
     *
     * @param id 用户编号
     * @return 是否删除成功
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Integer id) {
        // 删除用户记录
        Boolean success = false;
        // 返回是否更新成功
        return success;
    }

}
