package com.inter.service.impl;

import com.inter.dao.PlayerDao;
import com.inter.dao.UserDao;
import com.inter.dao.impl.PlayerDaoImpl;
import com.inter.dao.impl.UserDaoImpl;
import com.inter.domaim.PageBean;
import com.inter.domaim.Player;
import com.inter.domaim.User;
import com.inter.service.PlayerService;
import com.inter.service.UserService;

import java.util.Map;

/**
 * @author shgang
 * @date 2021-02-16 9:17 下午
 */
public class UserServiceImpl implements UserService {

    @Override
    public User login(User loginUser) {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findUserByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        return user;
    }

    @Override
    public void addPlayer(Player player) {
        PlayerDao playerDao = new PlayerDaoImpl();
        playerDao.add(player);
    }

    @Override
    public void deletePlayer(String id) {
        PlayerService service = new PlayerServiceImpl();
        service.deletePlayer(id);
    }

    @Override
    public Player findPlayerById(String id) {
        PlayerService service = new PlayerServiceImpl();
        return service.findPlayerById(id);
    }

    @Override
    public void updatePlayser(Player player) {
        PlayerService service = new PlayerServiceImpl();
        service.updatePlayer(player);
    }

    @Override
    public void deleteSelectedPlayers(String[] ids) {
        PlayerService service = new PlayerServiceImpl();
        service.deleteSelectedPlayers(ids);
    }

    @Override
    public PageBean<Player> findPlayerByPage(String currentPage, String rows, Map<String, String[]> map) {
        PlayerService service = new PlayerServiceImpl();
        PageBean<Player> pageBean = service.findPlayerByPage(currentPage, rows, map);
        return pageBean;
    }
}
