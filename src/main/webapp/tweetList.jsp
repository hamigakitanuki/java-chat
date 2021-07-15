<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "chat.*" %>
<%@ page import = "dao.bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="twtBean" class="dao.bean.ChatBean" scope = "session"/>
<h1>つぶやきアプリ</h1>
<h2>チャット画面</h2>
<p>
<table border = 1>
	<tr>
		<th>名前</th>
		<th>日付</th>
		<th>コメント</th>
	</tr>
<%
	ArrayList<ChatRecordBean> chatRecordArray = twtBean.getRecordArray();
	for(ChatRecordBean record : chatRecordArray){
%>
	<tr>
		<td></td>
		<td><%= record.getName() %></td>
		<td><%= record.getMessage() %></td>
	</tr>
<%
	}
%>
</table>

<form action="TweetAddServlet" method = "post">
<input type = "text" name = "comment" size = "30"><br>
<input type = "submit" value = "送信">
</form>
</body>
</html>