package com.inter.web.servlet;

import com.inter.domaim.Player;
import com.inter.domaim.User;
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
 * @date 2021-02-16 10:29 下午
 */
@WebServlet("/addPlayerServlet")
public class AddPlayerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
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
        //4.调用Service保存
        UserService service = new UserServiceImpl();
        service.addPlayer(player);
        //5.跳转到userListServlet
        response.sendRedirect(request.getContextPath() + "/playerListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
