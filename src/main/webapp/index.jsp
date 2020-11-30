<%--
  Created by IntelliJ IDEA.
  User: 凉介
  Date: 2020/11/18
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="user/fileupload1" method="post" enctype="multipart/form-data">
        选择文件：<input type="file"name="upload"/><br>
                <input type="submit" value="上传">
    </form>
    <form action="user/fileupload2" method="post" enctype="multipart/form-data">
        Spring选择文件：<input type="file"name="upload"/><br>
        <input type="submit" value="上传">
    </form>
</body>
</html>
