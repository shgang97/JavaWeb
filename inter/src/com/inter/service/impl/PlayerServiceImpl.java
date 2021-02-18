package com.inter.service.impl;

import com.inter.dao.PlayerDao;
import com.inter.dao.impl.PlayerDaoImpl;
import com.inter.domaim.Player;
import com.inter.service.PlayerService;

import java.util.List;

/**
 * @author shgang
 * @date 2021-02-16 4:14 下午
 */


public class PlayerServiceImpl implements PlayerService {

    private PlayerDao playerDao = new PlayerDaoImpl();

    @Override
    public List<Player> findAll() {
        //调用Dao完成查询
        return playerDao.findAll();
    }

    @Override
    public void deletePlayer(String id) {
        playerDao.delete(id);
    }

    @Override
    public Player findPlayerById(String id) {
        return playerDao.findById(Integer.parseInt(id));
    }

    @Override
    public void updatePlayer(Player player) {
        playerDao.update(player);
    }
}
