package com.inter.web.servlet;

import com.inter.domaim.User;
import com.inter.service.UserService;
import com.inter.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

/**
 * @author shgang
 * @date 2021-02-16 8:23 下午
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.验证码校验
        //2.1获取用户填写的验证码
        String verifycode = request.getParameter("verifycode");
        //2.2获取Servlet生成的验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码的一次性
        //2.3验证码校验
        if (!checkcode_server.equalsIgnoreCase(verifycode)) {//用户输入的验证码错误
            //提示信息
            request.setAttribute("login_msg", "验证码错误");
            //跳转登陆页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        //3.获取用户输入的信息封装User对象
        //3.1获取用户输入的信息
        Map<String, String[]> map = request.getParameterMap();
        //3.2封装User对象
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用Service查询
        UserService service = new UserServiceImpl();
        User user = service.login(loginUser);
        //5.判断是否登陆成功
        if (user != null) {//登陆成功
            //将用户存入session
            session.setAttribute("user", loginUser);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        } else {//登陆失败
            //提示信息
            request.setAttribute("login_msg", "用户名或密码错误");
            //跳转登陆页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
