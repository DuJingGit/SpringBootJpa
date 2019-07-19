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
            url: "/role/allrole",
            data : {
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                for(var i in aa.roleInfo){
                    $("#rolecheck").append("<input type='checkbox' id='"+aa.roleInfo[i].id+"' value='"+aa.roleInfo[i].id+"'>"+aa.roleInfo[i].role+"<br/>");
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
            url: "/user/userbyid",
            data : {
                "id":id
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                for(var i in aa.userInfo.roleList){
                    $("#"+aa.userInfo.roleList[i].id).attr("checked","checked");
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
            url: "/user/roletouser",
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
<table id="rolecheck">
    <input type='button' value='提交' onclick="show()"/>
</table>
</body>
</html>
