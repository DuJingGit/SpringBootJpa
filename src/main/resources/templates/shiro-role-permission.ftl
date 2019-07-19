<html>
<head>
    <title>user-role</title>
</head>
<script src="/js/jquery-1.8.0.js"></script>
<script src="/js/Ajax.js"></script>
<script>
    $(document).ready(function(){
        query();
        check();
    })

    function query() {
        $.ajax({
            type: 'GET',
            url: "/permission/allpermission",
            data : {
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                for(var i in aa.permissionInfo){
                    $("#permissioncheck").append("<input type='checkbox' id='"+aa.permissionInfo[i].id+"' value='"+aa.permissionInfo[i].id+"'>"+aa.permissionInfo[i].permissionname+"<br/>");
                }
            },
            error : function() {
                alert("error");
            }
        });
    }

    function check() {
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
                for(var i in aa.roleInfo.permissionList){
                    $("#"+aa.roleInfo.permissionList[i].id).attr("checked","checked");
                }
            },
            error : function() {
                alert("error");
            }
        });
    }

    function show(){
        var ids = [];
        var id = "${id}";
        $(":checked").each(function(i){
            ids[i] =$(this).val();
        });
        $.ajax({
            type: 'POST',
            url: "/role/permissiontorole",
            data : {
                _method:"PUT",
                "id":id,
                "ids":ids
            },
            traditional:true,
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
user-role
<br/>
<#include "welcome.ftl" >
<br/>
<table id="permissioncheck">
    <input type='button' value='提交' onclick="show()"/>
</table>
</body>
</html>
