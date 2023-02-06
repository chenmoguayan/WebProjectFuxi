<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/1/29
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
    ${login_error}
    <form action="user?m=login" method="post">
        <input type="text" name="username"><br>
        <input type="password" name="password"><br>
        <input type="submit" value="登录">
    </form>
</body>
</html>
