<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
</head>
<body>
<h1>つぶやきアプリ</h1>
<h2>ログイン</h2>
<form action="j_security_check" method = "post">
ログインID：<input type = "text" name = "j_username"><br>
パスワード：<input type = "password" name = "j_password"><br>
<input type = "submit" value = "ログイン">
<input type = "reset" value = "リセット">
</form>
</body>
</html>