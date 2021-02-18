package com.inter.web.servlet;

import com.inter.domaim.Player;
import com.inter.service.impl.PlayerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author shgang
 * @date 2021-02-16 4:07 下午
 */
@WebServlet("/playerListServlet")
public class PlayerListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("/playerListServlet");
        //1.调用PlayerService完成查询
        PlayerServiceImpl service = new PlayerServiceImpl();
        List<Player> players = service.findAll();
        //2.将list存入request域
        request.setAttribute("players", players);
        //3.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
