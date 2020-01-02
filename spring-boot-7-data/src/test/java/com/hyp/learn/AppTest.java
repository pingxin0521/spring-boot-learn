package com.hyp.learn;

import com.hyp.learn.data.DataApplication;
import com.hyp.learn.data.dao.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataApplication.class)
public class AppTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void shouldAnswerWithTrue() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testQuery() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tb_user");
        list.forEach(System.out::println);
    }

    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void testMabtis() {
        System.out.println(departmentMapper.getDeptByIdStep(1));
    }
}
