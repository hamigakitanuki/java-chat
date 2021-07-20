<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
</head>
<body>
<h2>ログイン</h2>
<form action="j_security_check" method = "post">
メールアドレス：<input type = "text" name = "j_username"><br>
パスワード：<input type = "password" name = "j_password"><br>
<input type = "submit" value = "ログイン">
<input type = "reset" value = "リセット">
</form>
</body>
</html>