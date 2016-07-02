<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String sender = "koka";//request.getParameter("sender");
		String receiver = "avto";//request.getParameter("receiver");
		String msg = "ludi";//request.getParameter("message");
	%>
	<p>
		From: &nbsp;
		<% out.println(sender); %>
	</p>
	<br>
	<p> 
		To: &nbsp;
		<% out.println(receiver); %>
	</p>
	<br>
	<br>
	<br>
	<p>
		<% out.println(msg); %>
	</p>
</body>
</html>