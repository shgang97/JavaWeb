package com.inter.test;

import com.inter.dao.UserDao;
import com.inter.domain.User;
import org.junit.Test;

/**
 * @author shgang
 * @date 2021-02-08 1:50 上午
 */
public class UserDaoTest {

    @Test
    public void login() {
        User loginUser = new User();
        loginUser.setUsername("shgang");
        loginUser.setPassword("inter0");

        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);

        System.out.println(user);

    }
}