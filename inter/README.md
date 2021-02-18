# 国际米兰球员信息展示综合案例
    1. 简单功能
        1. 列表查询
        2. 登录
        3. 添加
        4. 删除
        5. 修改
        
    2. 复杂功能
        1. 删除选中
        2. 分页查询
            * 好处：
                1. 减轻服务器内存的开销
                2. 提升用户体验
        3. 复杂条件查询


# 简单功能分析
## 1.列表查询
    1.1 需求：显示国际米兰球员信息
    1.2 设计：
        1. 技术选型：Servlet+JSP+MySQL+JDBCTemplate+Duird+BeanUtilS+tomcat
        2. 数据库设计：
            create database inter; -- 创建数据库（已创建）
            use inter; 			   -- 使用数据库
            create table player(   -- 创建表
                id int primary key auto_increment,
                name varchar(20) not null,
                gender varchar(5),
                age int,
                number	int,
                position varchar(32),
                email varchar(50)
            
            insert into player values(0,"shgang", "男",25, 23, "前锋", "shgang97@inter.com");



## 2. 登录
	1. 调整页面，加入验证码功能
	2. 代码实现


# 效果截图
! [avator](pic/inter.jpg)
## 1. 列表查询
! [avator](pic/playerlist.jpg)
## 2. 登录
! [avator](pic/login.jpg)
	


