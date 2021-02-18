package com.inter.test;

import com.inter.dao.impl.PlayerDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author shgang
 * @date 2021-02-16 4:38 下午
 */
public class PlayerDaoImplTest {

    @Test
    public void findAll() {
        PlayerDaoImpl playerDao = new PlayerDaoImpl();
        System.out.println(playerDao.findAll());
    }
}