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
    <title>AddRole</title>
</head>
<script src="<%=request.getContextPath() %>/js/jquery-1.8.0.js"></script>
<script>
    function ok(){
        var role = $("#role").val();
        $.ajax({
            type: 'POST',
            url: "/role/role",
            data : {
                "rolename":role
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
AddRole
<br/>
<%@include file="welcome.jsp"%>
<br/>
角色名:<input type="text" id="role"/>
<input type="submit" value="确定" onclick="ok()"/>
</body>
</html>
