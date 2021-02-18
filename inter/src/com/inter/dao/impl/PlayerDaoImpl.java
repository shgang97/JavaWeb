package com.inter.dao.impl;

import com.inter.dao.PlayerDao;
import com.inter.domaim.Player;
import com.inter.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author shgang
 * @date 2021-02-16 4:16 下午
 */

public class PlayerDaoImpl implements PlayerDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Player> findAll() {
        //使用JDBC操作数据库
        //1.定义sql
        String sql = "select * from player";
        //2.执行sql
        List<Player> players = template.query(sql, new BeanPropertyRowMapper<>(Player.class));

        return players;
    }

    @Override
    public void add(Player player) {
        //1.定义sql
        String sql = "insert into player values(null,?,?,?,?,?,?)";
        //2.执行sql
        template.update(sql, player.getName(), player.getGender(), player.getAge(), player.getNumber(), player.getPosition(), player.getEmail());
    }

    @Override
    public void delete(String id) {
        //1.定义sql
        String sql = "delete from player where id = ?";
        //2.执行sql
        template.update(sql, id);
    }

    @Override
    public Player findById(int id) {
        //1.定义sql
        String sql = "select * from player where id = ?";
        //2.执行sql
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Player.class), id);
    }

    @Override
    public void update(Player player) {
        //1.定义sql
        String sql = "update player set gender = ?, age = ?, number = ?, position = ?, email = ? where id = ?";
        //2.执行sql
        template.update(sql, player.getGender(), player.getAge(), player.getNumber(), player.getPosition(), player.getEmail(), player.getId());
    }
}
