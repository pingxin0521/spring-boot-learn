package com.hyp.learn.sharing;

import com.hyp.learn.sharing.dao.DictDao;
import com.hyp.learn.sharing.dao.OrderDao;
import com.hyp.learn.sharing.dao.UserDao;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.sharing
 * hyp create at 20-2-6
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SharingApplication.class})
public class OrderDaoTest {


    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    DictDao dictDao;

    @Test
    public void testInsertOrder() {
        for (int i = 1; i < 20; i++) {
            orderDao.insertOrder(new BigDecimal(i), 1L, "SUCCESS");
        }
    }

    @Test
    public void testSelectOrderbyIds() {
        List<Long> ids = new ArrayList<>();
        ids.add(373897739357913088L);
        ids.add(373897037306920961L);

        List<Map> maps = orderDao.selectOrderbyIds(ids);
        System.out.println(maps);
    }

    @Test
    public void testInsertOrder2() {
        for (int i = 0; i < 10; i++) {
            orderDao.insertOrder(new BigDecimal((i + 1) * 5), 1L, "WAIT_PAY");
        }
        for (int i = 0; i < 10; i++) {
            orderDao.insertOrder(new BigDecimal((i + 1) * 10), 2L, "WAIT_PAY");
        }
    }

    @Test
    public void testInsertUser() {
        for (int i = 0; i < 10; i++) {
            Long id = i + 1L;
            userDao.insertUser(id, "姓名" + id);
        }
    }

    @Test
    public void testSelectUserbyIds() {
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        List<Map> users = userDao.selectUserbyIds(userIds);
        System.out.println(users);
    }

    @Test
    public void testSelectUserInfobyIds() {
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        List<Map> users = userDao.selectUserInfobyIds(userIds);
        JSONArray jsonUsers = new JSONArray(users);
        System.out.println(jsonUsers);
    }

    @Test
    public void testInsertDict() {
        dictDao.insertDict(1L, "user_type", "0", "管理员");
        dictDao.insertDict(2L, "user_type", "1", "操作员");
    }

    @Test
    public void testDeleteDict() {
        dictDao.deleteDict(1L);
        dictDao.deleteDict(2L);
    }

}
