<html>
<head>
    <title>UpdUser</title>
</head>
<script src="/js/jquery-1.8.0.js"></script>
<script src="/js/Ajax.js"></script>
<script>
    $(document).ready(function(){
        query();
    })
    function query() {
        var id = "${id}";
        alert(id);
        $.ajax({
            type: 'GET',
            url: "/user/userbyid",
            data : {
                "id":id
            },
            dataType : "json",
            async : false,
            success : function(aa) {
                $("#id").val(aa.userInfo.id);
                $("#img").attr("src",aa.userInfo.headurl)
                $("#name").val(aa.userInfo.name);
                $("#password").val(aa.userInfo.password);
            },
            error : function() {
                alert("error");
            }
        });
    }
    // function ok(){
    //     var username = $("#username").val();
    //     var password = $("#password").val();
    //     var userid = $("#userid").val();
    //     $.ajax({
    //         type: 'POST',
    //         url: "/user/user",
    //         data : {
    //             _method:"PUT",
    //             "id":userid,
    //             "name":username,
    //             "password":password
    //
    //         },
    //         dataType : "json",
    //         async : false,
    //         success : function(aa) {
    //             alert(aa.updInfo);
    //         },
    //         error : function() {
    //             alert("error");
    //         }
    //     });
    // }

    // /**
    //  * 使用FormData
    //  */
    // function upd(){
    //     var formdata = new FormData($("#domeform")[0]);
    //     $.ajax({
    //         url: "/user/user",
    //         type: "PUST",
    //         data:formdata,
    //         dataType: "json",
    //         processData: false,  // 告诉jQuery不要去处理发送的数据
    //         contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
    //         success: function (res) {
    //             alert(res.updInfo);
    //         }
    //     })
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
UpdUser
<br/>
<#include "welcome.ftl" >
<br/>
<form action="/user/userfile" id="domeform" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" id="id"/>
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
