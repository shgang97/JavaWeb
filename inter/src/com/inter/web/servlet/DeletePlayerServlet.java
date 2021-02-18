package com.inter.web.servlet;

import com.inter.service.PlayerService;
import com.inter.service.UserService;
import com.inter.service.impl.PlayerServiceImpl;
import com.inter.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shgang
 * @date 2021-02-18 4:52 下午
 */
@WebServlet("/deletePlayerServlet")
public class DeletePlayerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String id = request.getParameter("id");
        //2.调用service
        UserService service = new UserServiceImpl();
        service.deletePlayer(id);
        //3.跳转到查询所有Servlet
        response.sendRedirect(request.getContextPath() + "/playerListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
