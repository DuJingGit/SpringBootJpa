<html>
<head>
    <title>user</title>
    <script src="/js/jquery-1.8.0.js"></script>
    <script src="/js/Ajax.js"></script>
</head>
<body>
User
<br/>
<#include "welcome.ftl" >
<br/>
账户名搜索:<input type="text" id="searchName">--<input type="button" value="确定" onclick="queryByName()">
<br/>
<table id="userTable"  border="1" width="60%" bgcolor="#e9faff" cellpadding="2">
</table>
<br/>
<a href="/user/adduser">Add</a>
<br/>
<input type="hidden" id="pageNumber" value=0>
<input type="hidden" id="pageSize" value=5>
<input type="hidden" id="pageSum" value=0>
<ul class="am-pagination am-pagination-right" id="page">
</ul>
<script >
    function del(id){
        $.ajax({
            type: 'POST',
            url: "/user/user",
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
    function queryByName() {
        $("#page").empty();
        var pageSize=$("#pageSize").val();
        var pageNumber=$("#pageNumber").val();
        var searchName=$("#searchName").val();
        $.ajax({
            type: 'GET',
            url: "/user/user",
            data : {
                "pageSize":pageSize,
                "pageNumber":pageNumber,
                "searchName":searchName
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                tableData(aa.userInfo);
            },
            error : function() {
                alert("error");
            }
        });
    }
    function query(){
        $("#page").empty();
        var pageSize=$("#pageSize").val();
        var pageNumber=$("#pageNumber").val();
        $.ajax({
            type: 'GET',
            url: "/user/user",
            data : {
                "pageSize":pageSize,
                "pageNumber":pageNumber
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                tableData(aa.userInfo);
            },
            error : function() {
                alert("error");
            }
        });
    }
    function tableData(aa){
        $("#userTable").empty();
        var pageNumber=$("#pageNumber").val();
        var pageSum=aa.pageSum;
        var pageSize=$("#pageSize").val();
        var pageWin=(Number(pageSum)-Number(pageNumber))/Number(pageSize);
        var pageCon=Number(pageSum)/Number(pageSize);
        var pageOrd=Number(pageNumber)/Number(pageSize);
        pageCon=Math.ceil(Number(pageCon));
        $("#pageSum").val(pageSum);
        $("#userTable").append("<caption>User</caption><tr >" +
                "<td>编号</td>" +
                "<td>账户名</td>" +
                "<td>头像</td>" +
                "<td>密码</td>" +
                "<td>操作</td>" +
                "</tr>")

        var iu=0;
        for(var i in aa.ts){
            iu=1;
            $("#userTable").append("<tr>" +
                    "<td>"+
                    aa.ts[i].id+
                    "</td>" +
                    "<td>"+
                    aa.ts[i].name+
                    "</td>" +
                    "<td><img src='"+
                    aa.ts[i].headurl+
                    "' width=100 height=100 /></td>" +
                    "<td>"+
                    aa.ts[i].password+
                    "</td>" +
                    "<td>"+
                    "<a href='javascript:del("+aa.ts[i].id+")'>Del</a>--<a href='/user/upduser/"+aa.ts[i].id+"'>Update</a>--<a href='/user/toroletouser/"+aa.ts[i].id+"'>RoleToUser</a>"+
                    "</td>" +
                    "</tr>")
        }
        if(iu==0){
            $("#userTable").append("<br/><br/>然而并没有任何信息");
        }
        var page="page";
        pageAjax(pageOrd,pageNumber,pageWin,pageSum,pageSize,pageCon,page);
    }
</script>
</body>
</html>
