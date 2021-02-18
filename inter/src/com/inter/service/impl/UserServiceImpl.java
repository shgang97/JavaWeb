package com.inter.service.impl;

import com.inter.dao.PlayerDao;
import com.inter.dao.UserDao;
import com.inter.dao.impl.PlayerDaoImpl;
import com.inter.dao.impl.UserDaoImpl;
import com.inter.domaim.Player;
import com.inter.domaim.User;
import com.inter.service.PlayerService;
import com.inter.service.UserService;

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
}
