<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<h2>Hello World!</h2>
<form action="${pageContext.request.contextPath}/file"
      method="post"
      enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="上传">
</form>

<a href="${pageContext.request.contextPath}/download">下载</a>
</body>
</html>
