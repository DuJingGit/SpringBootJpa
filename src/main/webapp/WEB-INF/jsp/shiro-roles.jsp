<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/10 0010
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>role</title>
</head>
<script src="<%=request.getContextPath() %>/js/jquery-1.8.0.js"></script>
<script src="<%=request.getContextPath() %>/js/Ajax.js"></script>
<script>
    function del(id) {
        $.ajax({
            type: 'POST',
            url: "/role/role",
            data : {
               _method:"DELETE",
               "id":id
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                alert(aa.delInfo);
            },
            error : function() {
                alert("error");
            }
        });
        query();
    }
    $(document).ready(function(){
        query();
    })
    function query(){
        $("#page").empty();
        var pageSize=$("#pageSize").val();
        var pageNumber=$("#pageNumber").val();
        $.ajax({
            type: 'GET',
            url: "/role/role",
            data : {
                "pageSize":pageSize,
                "pageNumber":pageNumber
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                tableData(aa.roleInfo);
            },
            error : function() {
                alert("error");
            }
        });
    }
    function tableData(aa){
        $("#roleTable").empty();
        var pageNumber=$("#pageNumber").val();
        var pageSum=aa.pageSum;
        var pageSize=$("#pageSize").val();
        var pageWin=(Number(pageSum)-Number(pageNumber))/Number(pageSize);
        var pageCon=Number(pageSum)/Number(pageSize);
        var pageOrd=Number(pageNumber)/Number(pageSize);
        pageCon=Math.ceil(Number(pageCon));
        $("#pageSum").val(pageSum);
        $("#roleTable").append("<caption>Role</caption><tr >" +
            "<td>编号</td>" +
            "<td>角色名</td>" +
            "<td>操作</td>" +
            "</tr>")

        var iu=0;
        for(var i in aa.ts){
            iu=1;
            $("#roleTable").append("<tr>" +
                "<td>"+
                aa.ts[i].id+
                "</td>" +
                "<td>"+
                aa.ts[i].role+
                "</td>" +
                "<td>"+
                "<a href='javascript:del("+aa.ts[i].id+")'>Del</a>--<a href='/role/updrole/"+aa.ts[i].id+"'>Update</a>--<a href='/role/topermissiontorole/"+aa.ts[i].id+"'>PermissionToRole</a>"+
                "</td>" +
                "</tr>")
        }
        if(iu==0){
            $("#roleTable").append("<br/><br/>然而并没有任何信息");
        }
        var page="page";
        pageAjax(pageOrd,pageNumber,pageWin,pageSum,pageSize,pageCon,page);
    }
</script>
<body>
Role
<br/>
<%@include file="welcome.jsp"%>
<br/>
<table id="roleTable"  border="1" width="60%" bgcolor="#e9faff" cellpadding="2">
</table>
<br/>
<a href="/role/addrole">Add</a>
<br/>
<input type="hidden" id="pageNumber" value=0>
<input type="hidden" id="pageSize" value=5>
<input type="hidden" id="pageSum" value=0>
<ul class="am-pagination am-pagination-right" id="page">
</ul>
</body>
</html>
