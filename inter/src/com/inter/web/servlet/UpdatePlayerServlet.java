package com.inter.web.servlet;

import com.inter.domaim.Player;
import com.inter.service.UserService;
import com.inter.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author shgang
 * @date 2021-02-18 6:57 下午
 */
@WebServlet("/updatePlayerServlet")
public class UpdatePlayerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取map
        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象
        Player player = new Player();
        try {
            BeanUtils.populate(player, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用service修改
        UserService service = new UserServiceImpl();
        service.updatePlayser(player);
        //5.跳转到查询所有Servlet
        response.sendRedirect(request.getContextPath() + "/playerListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
