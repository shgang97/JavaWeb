package com.inter.dao;

import com.inter.domaim.User;

import java.util.List;

/**
 * @author shgang
 * @date 2021-02-16 8:53 下午
 */
public interface UserDao {


    User findUserByUsernameAndPassword(String username, String password);
}
