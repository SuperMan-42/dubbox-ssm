package com.rrcp.core.user.dao;

import com.esotericsoftware.minlog.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author hpw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void test() throws Exception {
        while (true) {

        }
    }

    @Test
    public void testDao() throws Exception {
        Log.info(userDao.queryAll(1, 10).toString());
    }
}