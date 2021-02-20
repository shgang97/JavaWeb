package com.inter.service;

import com.inter.domaim.PageBean;
import com.inter.domaim.Player;
import com.inter.domaim.User;

import java.util.Map;

/**
 * @author shgang
 * @date 2021-02-16 9:17 下午
 */
public interface UserService {
    /**
     * 登陆方法
     * @param loginUser
     * @return
     */
    User login(User loginUser);

    /**
     * 保存player
     * @param player
     */
    void addPlayer(Player player);

    /**
     * 根据id删除Player
     * @param id
     */
    void deletePlayer(String id);

    /**
     * 根据id查询player
     * @param id
     * @return
     */
    Player findPlayerById(String id);

    /**
     * 修改球员信息
     * @param player
     */
    void updatePlayser(Player player);

    /**
     * 批量删除球员
     * @param ids
     */
    void deleteSelectedPlayers(String[] ids);

    PageBean<Player> findPlayerByPage(String currentPage, String rows, Map<String, String[]> map);
}
