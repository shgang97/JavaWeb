package com.inter.web.servlet;

import com.alibaba.druid.sql.visitor.functions.If;
import com.inter.domaim.PageBean;
import com.inter.domaim.Player;
import com.inter.service.UserService;
import com.inter.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author shgang
 * @date 2021-02-20 3:35 下午
 */
@WebServlet("/findPlayerByPageServlet")
public class FindPlayerByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        currentPage = currentPage == null || "".equals(currentPage) ? "1" : currentPage;
        rows = rows == null || "".equals(rows) ? "5" : rows;

        //获取条件查询参数
        Map<String, String[]> map = request.getParameterMap();

        //2.调用service
        UserService service = new UserServiceImpl();
        PageBean<Player> pageBean = service.findPlayerByPage(currentPage, rows, map);
        //3.将PageBean存入request
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("map", map);
        //4.转发到list.jsp
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
