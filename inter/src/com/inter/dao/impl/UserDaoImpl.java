package com.inter.dao.impl;

import com.inter.dao.UserDao;
import com.inter.domaim.User;
import com.inter.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author shgang
 * @date 2021-02-16 8:53 下午
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        //使用JDBC操作数据库
        //1.定义sql
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username,password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
