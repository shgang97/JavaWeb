package com.inter.dao;

import com.inter.domaim.Player;

import java.util.List;
import java.util.Map;

/**
 * @author shgang
 * @date 2021-02-16 4:15 下午
 */
public interface PlayerDao {

    List<Player> findAll();

    void add(Player player);

    void delete(String id);

    Player findById(int id);

    void update(Player player);

    void deleteSelected(String[] ids);


    int findTotalCount(Map<String, String[]> map);

    List<Player> findByPage(int start, int iRows, Map<String, String[]> map);
}
