<html>
<head>
    <title>AddRole</title>
</head>
<script src="/js/jquery-1.8.0.js"></script>
<script src="/js/Ajax.js"></script>
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
<#include "welcome.ftl" >
<br/>
角色名:<input type="text" id="role"/>
<input type="submit" value="确定" onclick="ok()"/>
</body>
</html>
