<html>
<head>
    <title>AddPermission</title>
</head>
<script src="/js/jquery-1.8.0.js"></script>
<script src="/js/Ajax.js"></script>
<script>
    function ok(){
        var permission = $("#permission").val();
        var url = $("#url").val();
        var method = $("#method").val();
        $.ajax({
            type: 'POST',
            url: "/permission/permission",
            data : {
                "permissionname":permission,
                "url":url,
                "method":method
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
AddPermission
<br/>
<#include "welcome.ftl" >
<br/>
权限名:<input type="text" id="permission"/>
URL:<input type="text" id="url"/>
请求方式:<input type="text" id="method"/>
<input type="submit" value="确定" onclick="ok()"/>
</body>
</html>
