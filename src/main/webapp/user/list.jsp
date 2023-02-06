<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主界面</title>
    <script>
        //第一种方法
        function page(currentPage){
            let mohu = document.getElementById("mohu").value;
            location = "user?m=list&currentPage=" + currentPage + "&mohu=" + mohu;
        }
        //第二种方法
        // function page(currentPage){
        //     document.getElementById("currentPage").value = currentPage;
        //     document.getElementById("form").submit();
        // }
    </script>
</head>
<body>
    欢迎${login_info}登录,<a href="user?m=logout">注销</a>
    <form action="user?m=list" method="post" id="form">
<%--        <input type="hidden" name="currentPage" id="currentPage">--%>
        <input type="text" id="mohu" name="mohu" value="${mohu}">
        <input type="submit" value="根据姓氏查询">
    </form>
    <table>
        <tr>
            <td></td>
            <td>ID</td>
            <td>用户名</td>
<%--            <td>密码</td>--%>
            <td>性别</td>
            <td>出生日期</td>
            <td>住址</td>
            <td>爱好</td>
            <td>
                <a href="user/add.jsp">添加</a>
            </td>
        </tr>
        <c:forEach var="u" items="${list}">
            <tr>
                <td></td>
                <td>${u.id}</td>
                <td>${u.username}</td>
<%--                <td>${u.password}</td>--%>
                <td>${u.sex}</td>
                <td>${u.birthday}</td>
                <td>${u.address}</td>
                <td>${u.hobby}</td>
                <td>
                    <a href="user?m=queryById&id=${u.id}">修改</a>
                    <a href="user?m=delete&id=${u.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%--<a href="user?m=list&currentPage=1">首页</a>
    <a href="user?m=list&currentPage=${page.prevPage}">上一页</a>
    <a href="user?m=list&currentPage=${page.nextPage}">下一页</a>
    <a href="user?m=list&currentPage=${page.lastPage}">尾页</a>--%>
    <input type="button" value="首页" onclick="page(1)">
    <input type="button" value="上一页" onclick="page(${page.prevPage})">
    <input type="button" value="下一页" onclick="page(${page.nextPage})">
    <input type="button" value="尾页" onclick="page(${page.lastPage})">

</body>
</html>
