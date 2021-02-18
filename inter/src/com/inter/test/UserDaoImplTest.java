package com.inter.test;

import com.inter.dao.UserDao;
import com.inter.dao.impl.UserDaoImpl;
import com.inter.domaim.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author shgang
 * @date 2021-02-16 8:57 下午
 */
public class UserDaoImplTest {

    @Test
    public void findUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findUserByUsernameAndPassword("shgang", "inter0");
        System.out.println(user);
    }
}