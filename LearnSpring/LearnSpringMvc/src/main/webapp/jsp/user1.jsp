<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>页面1</h1>
<form action="${pageContext.request.contextPath}/user/doAdd1" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><label>
                <input type="text" name="username">
            </label></td>
        </tr>
        <tr>
            <td>地址：</td>
            <td><label>
                <input type="text" name="address">
            </label></td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td><label>
                <input type="text" name="email">
            </label></td>
        </tr>
        <tr>
            <td>年龄：</td>
            <td><label>
                <input type="text" name="age">
            </label></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="添加">
            </td>
        </tr>
    </table>
</form>
</body>
</html>