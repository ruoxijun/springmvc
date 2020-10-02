<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--这里的utf-8是指服务器发送给客服端时的内容编码 --%>
<%@ page pageEncoding="utf-8" %>
<%--这里的utf-8是指 .jsp文件本身的内容编码 --%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="./resources/jquery-3.5.1.js"></script>
    <script>
        $(function (){
            $("#sub").click(function (){
                $.post({
                    // 请求地址
                    url:"${pageContext.request.contextPath}/allBook",
                    // 携带数据
                    data:{mgs:$("#mgs").val()},
                    // 请求成功函数，data参数为请求响应的数据
                    success:function(data){
                        console.log(data);
                    },
                    // 请求失败
                    error:function (){
                        console.log("error");
                    }
                });
            });
        });
    </script>
</head>
<body>
<input type="text" id="mgs" name="mgs">
<input type="button" id="sub" value="提交">
</body>
</html>
