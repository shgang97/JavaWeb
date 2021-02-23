package com.inter.web.filter;

import com.inter.domaim.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author shgang
 * @date 2021-02-21 5:50 下午
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //0.强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        //1.获取资源请求路径
        String uri = request.getRequestURI();
        //2.判断是否包含登陆相关资源路径
        if (uri.contains("/login.jsp") || uri.contains("/loginServlet") ||uri.contains("/js/") ||uri.contains("/css/") ||uri.contains("/logingServlet") ||uri.contains("/fonts") ||uri.contains("/checkCodeServlet")) {//包含，用户就是想登陆，放行
            chain.doFilter(req, resp);
        } else {//不包含，需要验证用户是否登陆
            //3.从session中获取user
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {//已登陆，放行
                chain.doFilter(req, resp);
            } else {//没有登陆，跳转到登陆页面
                    request.setAttribute("login_mag", "您尚未登陆，请登陆");
                    request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
