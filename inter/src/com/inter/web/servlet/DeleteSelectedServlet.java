package com.inter.web.servlet;

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
 * @date 2021-02-19 2:29 下午
 */
@WebServlet("/deleteSelectedServlet")
public class DeleteSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取所有的id
        String[] ids = request.getParameterValues("pid");
        //2.调用service删除
        UserService service = new UserServiceImpl();
        service.deleteSelectedPlayers(ids);
        //3.跳转查询所有的Servlet
        response.sendRedirect(request.getContextPath() + "/playerListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
