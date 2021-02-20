<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <!-- 使用Edge最新的浏览器的渲染方式 -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
        width: 默认宽度与设备的宽度相同
        initial-scale: 初始的缩放比，为1:1 -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>用户信息管理系统</title>

        <!-- 1. 导入CSS的全局样式 -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
        <script src="js/jquery-2.1.0.min.js"></script>
        <!-- 3. 导入bootstrap的js文件 -->
        <script src="js/bootstrap.min.js"></script>
        <style type="text/css">
            td, th {
                text-align: center;
            }
        </style>

        <script>
            function deletePlayer(id) {
                //用户删除安全提示
                if (confirm("您确定要删除id为" + id + "的球员吗？")) {
                    location.href = "${pageContext.request.contextPath}/deletePlayerServlet?id=" + id;
                }
            }

            window.onload = function () {
                //给删除选中按钮添加单击事件
                document.getElementById("delSelected").onclick = function () {
                    if (confirm("您确定要删除选中条目吗？")) {
                        var flag = false;
                        //判断是否有选中条目
                        var cbs = document.getElementsByName("pid");
                        for (var i = 0; i < cbs.length; i++) {
                            if (cbs[i].checked) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            //表单提交
                            document.getElementById("form").submit();
                        }
                    }
                }

                //1.获取第一个cb
                document.getElementById("firstCb").onclick = function () {
                    //2.获取下拉列表所有的cb
                    var cbs = document.getElementsByName("pid");
                    //3.遍历
                    for (var i = 0; i < cbs.length; i++) {
                        //4.设置这些cbs[i]的checked状态=firstCb.checked
                        cbs[i].checked = this.checked;
                    }
                }
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h3 style="text-align: center">球员信息列表</h3>

            <!-- 添加筛选表单
            直接从https://v3.bootcss.com/css/#forms找到相关样式复制修改-->
            <div style="float: left;">
                <form class="form-inline" action="${pageContext.request.contextPath}/findPlayerByPageServlet" method="post">
                    <div class="form-group">
                        <label for="inputname">姓名</label>
                        <input type="text" value="${map.name[0]}" name="name" class="form-control" id="inputname">
                    </div>

                    <div class="form-group">
                        <label for="inputposition">位置</label>
                        <input type="text"value="${map.position[0]}"  name="position" class="form-control" id="inputposition">
                    </div>
                    <div class="form-group">
                        <label for="inputEmail">邮箱</label>
                        <input type="email" value="${map.email[0]}" name="email" class="form-control" id="inputEmail">
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>
            </div>

            <div style="float: right;margin: 5px">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加球员</a>
                <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
            </div>
            <%--提交form表单，会自动提交id--%>
            <form id="form" action="${pageContext.request.contextPath}/deleteSelectedServlet" method="post">
                <table border="1" class="table table-bordered table-hover">
                    <tr class="success">
                        <th><input type="checkbox" id="firstCb"></th>
                        <th>编号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>号码</th>
                        <th>位置</th>
                        <th>邮箱</th>
                        <th>操作</th>
                    </tr>

                    <c:forEach items="${pageBean.list}" var="player" varStatus="s">
                        <tr>
                            <th><input type="checkbox" name="pid" value="${player.id}"></th>
                            <td>${s.count}</td>
                            <td>${player.name}</td>
                            <td>${player.gender}</td>
                            <td>${player.age}</td>
                            <td>${player.number}</td>
                            <td>${player.position}</td>
                            <td>${player.email}</td>
                            <td>
                                <a class="btn btn-default btn-sm"
                                   href="${pageContext.request.contextPath}/findPlayerServlet?id=${player.id}">修改</a>&nbsp;
                                    <%--定义方法，通过deleteUser(${player.id})访问servlet，并在方法中添加删除确认提示框--%>
                                <a class="btn btn-default btn-sm" href="javascript:deletePlayer(${player.id});">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="12" align="center"></td>
                    </tr>
                </table>
            </form>

            <div>
                <!--添加分页工具条
                直接从https://v3.bootcss.com/components/#pagination找到分页工具条复制修改
                -->
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${pageBean.currentPage == 1}">
                        <li class="disabled">

                            </c:if>
                            <c:if test="${pageBean.currentPage != 1}">
                        <li>
                            </c:if>

                            <a href="${pageContext.request.contextPath}/findPlayerByPageServlet?currentPage=${1 < pageBean.currentPage ? pageBean.currentPage - 1 : 1}&rows=5&name=${map.name[0]}&position=${map.position[0]}&email=${map.email[0]}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${pageBean.totalPage}" var="i">
                            <c:if test="${pageBean.currentPage == i}">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/findPlayerByPageServlet?currentPage=${i}&rows=5&name=${map.name[0]}&position=${map.position[0]}&email=${map.email[0]}">${i}</a>
                                </li>
                            </c:if>

                            <c:if test="${pageBean.currentPage != i}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/findPlayerByPageServlet?currentPage=${i}&rows=5&name=${map.name[0]}&position=${map.position[0]}&email=${map.email[0]}">${i}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${pageBean.currentPage == pageBean.totalPage}">
                        <li class="disabled">
                            </c:if>
                            <c:if test="${pageBean.currentPage != pageBean.totalPage}">
                        <li>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/findPlayerByPageServlet?currentPage=${pageBean.currentPage < pageBean.totalPage ? pageBean.currentPage + 1 : pageBean.totalPage}&rows=5&name=${map.name[0]}&position=${map.position[0]}&email=${map.email[0]}"
                               aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <span style="font-size: 25px;margin-left: 4px">
                    共${pageBean.totalCount}条记录，共${pageBean.totalPage}页
                </span>
                    </ul>
                </nav>
            </div>

        </div>
    </body>
</html>
