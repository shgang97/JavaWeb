package com.shgang.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shgang
 * @date 2021-02-10 4:41 上午
 *
 * 案例：记住上一次访问时间
 * 		1. 需求：
 * 			1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
 * 			2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串
 *
 * 		2. 分析：
 * 			1. 可以采用Cookie来完成
 * 			2. 在服务器中的Servlet判断是否有一个名为lastTime的cookie
 * 				1. 有：不是第一次访问
 * 					1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
 * 					2. 写回Cookie：lastTime=2018年6月10日11:50:01
 * 				2. 没有：是第一次访问
 * 					1. 响应数据：您好，欢迎您首次访问
 * 					2. 写回Cookie：lastTime=2018年6月10日11:50:01
 */
@WebServlet("/cookieServlet")
public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的消息体的数据格式以及编码
        response.setContentType("text/html;charset=utf-8");
        //1.获取所有的Cookie
        Cookie[] cookies = request.getCookies();
        //2.遍历cookies数组
        boolean flag = false;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                //3.获取cookie的名称
                String name = cookie.getName();
                //4.判断名称是否是lastTime
                if ("lastTime".equals(name)) {//不是第一次访问
                    flag = true;

                    //5.1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
                    String value = cookie.getValue();
                    value = URLDecoder.decode(value, "utf-8");
                    response.getWriter().write("<h1>欢迎回来，您上次访问时间为:" + value + "<h1/>");

                    //设置cookie的value
                    //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String newTime = simpleDateFormat.format(date);
                    //URL编码
                    newTime = URLEncoder.encode(newTime, "utf-8");
                    cookie.setValue(newTime);
                    response.addCookie(cookie);
                    //设置cookie的存活时间
                    cookie.setMaxAge(60 * 60 * 24 *30);
                    break;
                }
            }
        }
        if (cookies == null || cookies.length == 0 || !flag) {//没有，第一次访问
            //设置cookie的value
            //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String newTime = simpleDateFormat.format(date);
            //URL编码
            newTime = URLEncoder.encode(newTime, "utf-8");
            Cookie cookie = new Cookie("lastTime", newTime);
            cookie.setValue(newTime);
            response.addCookie(cookie);

            //设置cookie的存活时间
            cookie.setMaxAge(60 * 60 * 24 *30);
            response.getWriter().write("<h1>您好，欢迎您首次访问<h1/>");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
