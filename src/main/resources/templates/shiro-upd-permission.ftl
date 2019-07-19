<html>
<head>
    <title>UpdPermission</title>
</head>
<script src="/js/jquery-1.8.0.js"></script>
<script src="/js/Ajax.js"></script>
<script>
    $(document).ready(function(){
        query();
    })
    function query() {
        var id = "${id}";
        $.ajax({
            type: 'GET',
            url: "/permission/permissionbyid",
            data : {
                "id":id
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                $("#permissionid").val(aa.permissionInfo.id);
                $("#permission").val(aa.permissionInfo.permission);
                $("#url").val(aa.permissionInfo.url);
                $("#method").val(aa.permissionInfo.method);
            },
            error : function() {
                alert("error");
            }
        });
    }
    function ok(){
        var id = $("#permissionid").val();
        var permission = $("#permission").val();
        var url = $("#url").val();
        var method = $("#method").val();
        $.ajax({
            type: 'POST',
            url: "/permission/permission",
            data : {
                _method:"PUT",
                "id":id,
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
UpdPermission
<br/>
<#include "welcome.ftl" >
<br/>
<input type="hidden" id="permissionid"/>
权限名:<input type="text" id="permission"/>
URL:<input type="text" id="url"/>
请求方式:<input type="text" id="method"/>
<input type="submit" value="确定" onclick="ok()"/>
</body>
</html>
