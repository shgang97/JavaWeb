<%--
  @author: shgang
  @date: 2021/2/16
  @time: 10:10 下午
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改用户</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </head>
    <body>
        <div class="container" style="width: 400px;">
            <h3 style="text-align: center;">修改球员信息</h3>
            <form action="${pageContext.request.contextPath}/updatePlayerServlet" method="post">
                <!-- 隐藏域，提交id-->
                <input type="hidden" name="id" value="${player.id}">
                <div class="form-group">
                    <label for="name">姓名：</label>
                    <%--readonly="readonly"设置name为不可修改--%>
                    <input type="text" class="form-control" id="name" name="name" value="${player.name}"
                           readonly="readonly"/>
                </div>

                <div class="form-group">
                    <label>性别：</label>
                    <c:if test="${player.gender=='男'}">
                        <input type="radio" name="gender" value="男" checked/>男
                        <input type="radio" name="gender" value="女"/>女
                    </c:if>

                    <c:if test="${player.gender=='女'}">
                        <input type="radio" name="gender" value="男"/>男
                        <input type="radio" name="gender" value="女" checked/>女
                    </c:if>
                </div>

                <div class="form-group">
                    <label for="age">年龄：</label>
                    <input type="text" class="form-control" id="age" name="age" value="${player.age}"/>
                </div>


                <div class="form-group">
                    <label for="position">位置：</label>
                    <select name="position" class="form-control" id="position">
                        <c:if test="${player.position=='前锋'}">
                            <option value="前锋" selected>前锋</option>
                            <option value="中场">中场</option>
                            <option value="后卫">后卫</option>
                            <option value="门将">门将</option>
                        </c:if>
                        <c:if test="${player.position=='中场'}">
                            <option value="前锋">前锋</option>
                            <option value="中场" selected>中场</option>
                            <option value="后卫">后卫</option>
                            <option value="门将">门将</option>
                        </c:if>
                        <c:if test="${player.position=='后卫'}">
                            <option value="前锋">前锋</option>
                            <option value="中场">中场</option>
                            <option value="后卫" selected>后卫</option>
                            <option value="门将">门将</option>
                        </c:if>
                        <c:if test="${player.position=='门将'}">
                            <option value="前锋">前锋</option>
                            <option value="中场">中场</option>
                            <option value="后卫">后卫</option>
                            <option value="门将" selected>门将</option>
                        </c:if>
                        <c:if test="${player.position==null}">
                            <option value="前锋" selected>前锋</option>
                            <option value="中场">中场</option>
                            <option value="后卫">后卫</option>
                            <option value="门将">门将</option>
                        </c:if>
                    </select>
                </div>

                <div class="form-group">
                    <label for="number">号码：</label>
                    <input type="text" class="form-control" name="number" id="number" value="${player.number}"/>
                </div>

                <div class="form-group">
                    <label for="email">Email：</label>
                    <input type="text" class="form-control" name="email" id="email" value="${player.email}"/>
                </div>

                <div class="form-group" style="text-align: center">
                    <input class="btn btn-primary" type="submit" value="提交"/>
                    <input class="btn btn-default" type="reset" value="重置"/>
                    <input class="btn btn-default" type="button" value="返回"/>
                </div>
            </form>
        </div>
    </body>
</html>
