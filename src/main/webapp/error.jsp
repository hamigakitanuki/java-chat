<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>エラー</title>
</head>
<body>
<h1>エラー画面</h1>
<%
	Exception e = (Exception) session.getAttribute("Except");
%>
<p><%= e.getMessage() %>
<button type="button" onclick="tweetList.jsp">戻る</button>
</body>
</html>