# JavaWeb
Some demos and projects in studying javaweb.


## logoninter
  ###国际米兰官方网站用户登陆案例：
    案例需求：
      1.编写login.html登录页面
        username & password 两个输入框
      2.使用Druid数据库连接池技术,操作mysql，inter数据库中user表
      3.使用JdbcTemplate技术封装JDBC
      4.登录成功跳转到SuccessServlet展示：登录成功！用户名,欢迎您
      5.登录失败跳转到FailServlet展示：登录失败，用户名或密码错误

  ### 建立数据库环境`
  create database inter;
  use inter；
  create table user(id int primary key auto_increment, username varchar(32)unique not null, password varchar(32) not null);
  insert into user values(0,"shgang", "inter0");
`
