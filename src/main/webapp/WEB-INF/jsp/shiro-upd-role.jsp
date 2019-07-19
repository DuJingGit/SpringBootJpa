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
    <title>UpdRole</title>
</head>
<script src="<%=request.getContextPath() %>/js/jquery-1.8.0.js"></script>
<script>
    $(document).ready(function(){
        query();
    })
    function query() {
        var id = "${id}";
        $.ajax({
            type: 'GET',
            url: "/role/rolebyid",
            data : {
                "id":id
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                $("#roleid").val(aa.roleInfo.id);
                $("#role").val(aa.roleInfo.role);
            },
            error : function() {
                alert("error");
            }
        });
    }
    function ok(){
        var roleid = $("#roleid").val();
        var rolename = $("#role").val();
        $.ajax({
            type: 'POST',
            url: "/role/role",
            data : {
                _method:"PUT",
                "id":roleid,
                "rolename":rolename
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
UpdRole
<br/>
<%@include file="welcome.jsp"%>
<br/>
<input type="hidden" id="roleid"/>
角色名:<input type="text" id="role"/>
<input type="submit" value="确定" onclick="ok()"/>
</body>
</html>
