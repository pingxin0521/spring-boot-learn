package com.hyp.learn.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.test
 * hyp create at 19-12-22
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UnitTestApplicationTests {

    @Test(timeout = 1000)
    public void testTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        log.info("Complete");
    }

    @Test(expected = NullPointerException.class)
    public void testNullException() {
        throw new NullPointerException();
    }





}
