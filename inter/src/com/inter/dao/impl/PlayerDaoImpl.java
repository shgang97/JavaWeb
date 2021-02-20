package com.inter.dao.impl;

import com.inter.dao.PlayerDao;
import com.inter.domaim.Player;
import com.inter.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Override
    public void deleteSelected(String[] ids) {
        for (String id : ids) {
            delete(id);
        }
    }

    @Override
    public int findTotalCount(Map<String, String[]> map) {
        //1.定义模版初始化sql
        String initSql = "select count(*) from player where 1 = 1 ";
        StringBuilder sql = new StringBuilder(initSql);
        //2.遍历map
        Set<String> keySet = map.keySet();
        //定义参数集合
        ArrayList<String> params = new ArrayList<>();
        for (String key : keySet) {
            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) continue;
            //获取value
            String value = map.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                sql.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }

        return template.queryForObject(sql.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<Player> findByPage(int start, int rows, Map<String, String[]> map) {
        String initSql = "select * from player where 1 = 1 ";
        StringBuilder sql = new StringBuilder(initSql);
        //2.遍历map
        Set<String> keySet = map.keySet();
        //定义参数集合
        ArrayList<Object> params = new ArrayList<>();
        for (String key : keySet) {
            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) continue;
            //获取value
            String value = map.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                sql.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        sql.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        System.out.println(params);
        System.out.println(sql.toString());
        return template.query(sql.toString(), new BeanPropertyRowMapper<Player>(Player.class), params.toArray());
    }


}
