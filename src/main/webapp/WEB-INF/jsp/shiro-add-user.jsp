<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/12 0012
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddUser</title>
</head>
<script src="<%=request.getContextPath() %>/js/jquery-1.8.0.js"></script>
<script>
    function ok(){
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            type: 'POST',
            url: "/user/user",
            data : {
                "name":username,
                "password":password

            },
            dataType : "json",
            async : false,
            success : function(aa) {
                alert(aa.addInfo);
            },
            error : function() {
                alert("error");
            }
        });
    }
</script>
<body>
AddUser
<br/>
<%@include file="welcome.jsp"%>
<br/>
用户名:<input type="text" id="username"/>
密码:<input type="password" id="password"/>
<input type="submit" value="确定" onclick="ok()"/>
</body>
</html>
