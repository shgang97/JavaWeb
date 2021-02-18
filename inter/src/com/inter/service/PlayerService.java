package com.inter.service;

import com.inter.domaim.Player;

import java.util.List;

/**
 * @author shgang
 * @date 2021-02-16 4:12 下午
 *
 * 球员管理的业务借接口
 */
public interface PlayerService {

    /**
     * 查询所有球员信息
     * @return
     */
    public List<Player> findAll();

    void deletePlayer(String id);

    Player findPlayerById(String id);

    void updatePlayer(Player player);
}
