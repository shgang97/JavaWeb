package com.inter.web.servlet;

import com.inter.domaim.Player;
import com.inter.service.UserService;
import com.inter.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shgang
 * @date 2021-02-18 5:37 下午
 */
@WebServlet("/findPlayerServlet")
public class FindPlayerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String id = request.getParameter("id");
        //2.调用Service查询
        UserService service = new UserServiceImpl();
        Player player = service.findPlayerById(id);
        //3.将player存入request    }
        request.setAttribute("player", player);
        //4.转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
