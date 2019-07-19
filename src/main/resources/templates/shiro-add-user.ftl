<html>
<head>
    <title>AddUser</title>
</head>
<script src="/js/jquery-1.8.0.js"></script>
<script src="/js/Ajax.js"></script>
<script>
    $(document).ready(function(){
        var state = "${addInfo}";
        if (state == 1){
            alert("新增成功！！！");
        }else if (state == 0){
            alert("新增失败！！！");
        }
    })
    // function ok(){
    //     layer.msg('添加成功。');
    //     var username = $("#username").val();
    //     var password = $("#password").val();
    //     $.ajax({
    //         type: 'POST',
    //         url: "/user/user",
    //         data : {
    //             "name":username,
    //             "password":password
    //
    //         },
    //         dataType : "json",
    //         async : false,
    //         success : function(aa) {
    //             alert(aa.addInfo);
    //         },
    //         error : function() {
    //             alert("error");
    //         }
    //     });
    // }
    function chooseImg(file){
        var file=file.files[0];
        var reader=new FileReader();
        reader.readAsDataURL(file);
        reader.onload=function(){
            var img=document.getElementById('img');
            img.src=this.result
        };
    }
</script>
<body>
AddUser
<br/>
<#include "welcome.ftl" >
<br/>
<form action="/user/user" id="domeform" method="post" enctype="multipart/form-data">
    <img id="img" src="" width=100 height=100 onclick="f.click()" />
    <input id="f" type="file" name="file" onchange="chooseImg(this)" style="display:none">
    <br/>
    用户名:<input type="text" name="name" id="name"/>
    <br/>
    密码:<input type="password" name="password" id="password"/>
    <br/>
    <input type="submit" value="确定"/>
</form>
</body>
</html>
