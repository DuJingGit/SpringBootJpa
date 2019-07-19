<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <title>登录</title>
    <script>

    </script>
</head>

<body bgcolor="black">
<h1 align="center">用户登录</h1>
<hr>
<br>
<form action="shiro-login" method="post">
    <div align="center">
        <table border="1">
            <tbody>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input value="登录"
                                                      type="submit"> <input value="重置" type="reset"></td>
            </tr>
            </tbody>
        </table>
    </div>
</form>
<hr>
</body>
</html>
