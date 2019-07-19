<html>
<head>
    <title>permission</title>
</head>
<script src="/js/jquery-1.8.0.js"></script>
<script src="/js/Ajax.js"></script>
<script>
    function del(id) {
        $.ajax({
            type: 'POST',
            url: "/permission/permission",
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
            url: "/permission/permission",
            data : {
                "pageSize":pageSize,
                "pageNumber":pageNumber
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                tableData(aa.permissionInfo);
            },
            error : function() {
                alert("error");
            }
        });

    }
    function tableData(aa){
        $("#permissionTable").empty();
        var pageNumber=$("#pageNumber").val();
        var pageSum=aa.pageSum;
        var pageSize=$("#pageSize").val();
        var pageWin=(Number(pageSum)-Number(pageNumber))/Number(pageSize);
        var pageCon=Number(pageSum)/Number(pageSize);
        var pageOrd=Number(pageNumber)/Number(pageSize);
        pageCon=Math.ceil(Number(pageCon));
        $("#pageSum").val(pageSum);
        $("#permissionTable").append("<caption>Permission</caption><tr >" +
            "<td>编号</td>" +
            "<td>权限名</td>" +
            "<td>URL</td>" +
            "<td>请求方式</td>" +
            "<td>操作</td>" +
            "</tr>")

        var iu=0;
        for(var i in aa.ts){
            iu=1;
            $("#permissionTable").append("<tr>" +
                "<td>"+
                aa.ts[i].id+
                "</td>" +
                "<td>"+
                aa.ts[i].permission+
                "</td>" +
                "<td>"+
                aa.ts[i].url+
                "</td>" +
                "<td>"+
                aa.ts[i].method+
                "</td>" +
                "<td>"+
                "<a href='javascript:del("+aa.ts[i].id+")'>Del</a>--<a href='/permission/updpermission/"+aa.ts[i].id+"'>Update</a>"+
                "</td>" +
                "</tr>")
        }
        if(iu==0){
            $("#permissionTable").append("<br/><br/>然而并没有任何信息");
        }
        var page="page";
        pageAjax(pageOrd,pageNumber,pageWin,pageSum,pageSize,pageCon,page);
    }
</script>
<body>
Permission
<br/>
<#include "welcome.ftl" >
<br/>
<table id="permissionTable"  border="1" width="60%" bgcolor="#e9faff" cellpadding="2">
</table>
<br/>
<a href="/permission/addpermission">Add</a>
<br/>
<input type="hidden" id="pageNumber" value=0>
<input type="hidden" id="pageSize" value=5>
<input type="hidden" id="pageSum" value=0>
<ul class="am-pagination am-pagination-right" id="page">
</ul>
</body>
</html>
