<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/12 0012
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdUser</title>
</head>
<script src="<%=request.getContextPath() %>/js/jquery-1.8.0.js"></script>
<script>
    $(document).ready(function(){
        query();
    })
    function query() {
        var id = "${id}";
        alert(id);
        $.ajax({
            type: 'GET',
            url: "/user/userbyid",
            data : {
                "id":id
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                $("#userid").val(aa.userInfo.id);
                $("#username").val(aa.userInfo.name);
                $("#password").val(aa.userInfo.password);
            },
            error : function() {
                alert("error");
            }
        });
    }
    function ok(){
        var username = $("#username").val();
        var password = $("#password").val();
        var userid = $("#userid").val();
        $.ajax({
            type: 'POST',
            url: "/user/user",
            data : {
                _method:"PUT",
                "id":userid,
                "name":username,
                "password":password

            },
            dataType : "json",
            async : false,
            success : function(aa) {
                alert(aa.updInfo);
            },
            error : function() {
                alert("error");
            }
        });
    }
</script>
<body>
UpdUser
<br/>
<%@include file="welcome.jsp"%>
<br/>
<input type="hidden" id="userid"/>
用户名:<input type="text" id="username"/>
密码:<input type="password" id="password"/>
<input type="submit" value="确定" onclick="ok()"/>
</body>
</html>
