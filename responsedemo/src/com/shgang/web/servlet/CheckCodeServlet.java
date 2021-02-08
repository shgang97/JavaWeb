package com.shgang.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author shgang
 * @date 2021-02-09 12:47 上午
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int width = 100;
        int height = 50;

        //1.创建一对象，在内存中代表一个图片（验证码图片对象）
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //2.美化图片
        //2.0创建画笔对象
        Graphics graphics = image.getGraphics();
        //2.1填充背景色
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, width, height);
        //2.2画边框
        graphics.setColor(Color.blue);
        graphics.drawRect(0, 0, width - 1, height - 1);
        //2.3写验证码
        String codes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            char ch = codes.charAt(random.nextInt(codes.length()));
            graphics.drawString(ch + "", width / 5 * i + 20, height / 2);
        }
        //2.4画干扰线
        graphics.setColor(Color.green);
        for (int i  = 0; i < 5; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1, x2, y1, y2);
        }

        //3.将图片输出展示到页面
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
