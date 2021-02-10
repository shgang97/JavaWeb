# JavaWeb
Some demos and projects in studying javaweb.


## logoninter
  ### 国际米兰官方网站用户登陆案例：
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

## cookiedemo
  ### 记录上一次访问时间案例
		1. 需求：
			1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
			2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串

		2. 分析：
			1. 可以采用Cookie来完成
			2. 在服务器中的Servlet判断是否有一个名为lastTime的cookie
				1. 有：不是第一次访问
					1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
					2. 写回Cookie：lastTime=2018年6月10日11:50:01
				2. 没有：是第一次访问
					1. 响应数据：您好，欢迎您首次访问
					2. 写回Cookie：lastTime=2018年6月10日11:50:01
