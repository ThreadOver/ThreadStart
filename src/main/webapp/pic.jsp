
<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
 
<head>
 
<meta charset="UTF-8">
 
<title>更换头像</title>
 
</head>
 
<body>
 
         <form action="pic" method="post"enctype="multipart/form-data">
 
                     本地目录：<input  type="file" name="uploadFile">
 
           <img src="${image_path}" width="200" height="200">
 
                <input type="submit" value="上传头像"/>
 
   </form>
 
</body>
 
</html>