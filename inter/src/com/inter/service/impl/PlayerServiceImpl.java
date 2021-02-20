package com.inter.service.impl;

import com.inter.dao.PlayerDao;
import com.inter.dao.impl.PlayerDaoImpl;
import com.inter.domaim.PageBean;
import com.inter.domaim.Player;
import com.inter.service.PlayerService;

import java.util.List;
import java.util.Map;

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

    @Override
    public void deleteSelectedPlayers(String[] ids) {
        playerDao.deleteSelected(ids);
    }

    @Override
    public PageBean<Player> findPlayerByPage(String _currentPage, String _rows, Map<String, String[]> map) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //1.创建空到PageBean对象
        PageBean<Player> pageBean = new PageBean<>();
        //2.设置参数
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        //3.调用dao查询总记录数
        int totalCount = playerDao.findTotalCount(map);
        pageBean.setTotalCount(totalCount);
        //4.调用dao查询list集合
        //计算开始的记录数
        int start = (currentPage - 1) * rows;
        List<Player> list = playerDao.findByPage(start, rows, map);
        pageBean.setList(list);
        //5.计算总页码
        int totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
}
