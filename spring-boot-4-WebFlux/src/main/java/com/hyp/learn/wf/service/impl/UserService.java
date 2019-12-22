package com.hyp.learn.wf.service.impl;

import com.hyp.learn.wf.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.wf.service.impl
 * hyp create at 19-12-22
 **/
@Service
public class UserService {

    public UserVO get(Integer id) {
        return new UserVO().setId(id).setUsername("test");
    }

}
