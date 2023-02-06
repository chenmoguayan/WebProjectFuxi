<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/1/29
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>修改界面</title>
</head>
<body>
    <form action="user?m=update" method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="text" name="username" value="${user.username}">
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="password" name="password" value="${user.password}">
                </td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    <input type="radio" name="sex" value="男" ${user.sex=='男'?'checked':''}>男
                    <input type="radio" name="sex" value="女" ${user.sex=='女'?'checked':''}>女
                </td>
            </tr>
            <tr>
                <td>出生日期：</td>
                <td>
                    <input type="date" name="birthday" value="${user.birthday}">
                </td>
            </tr>
            <tr>
                <td>住址：</td>
                <td>
                    <input type="text" name="address" value="${user.address}">
                </td>
            </tr>
            <tr>
                <td>爱好：</td>
                <td>
                    <input type="checkbox" name="hobby" value="抽烟"${fn:contains(user.hobby, '抽烟') ? 'checked':''}>抽烟
                    <input type="checkbox" name="hobby" value="喝酒"${fn:contains(user.hobby, '喝酒') ? 'checked':''}>喝酒
                    <input type="checkbox" name="hobby" value="烫头"${fn:contains(user.hobby, '烫头') ? 'checked':''}>烫头
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="修改">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
