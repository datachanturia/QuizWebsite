<%@page import="Model.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<% String sender = request.getParameter("sender"); %>
	<p>
		To:
		<%
		String reciever = request.getParameter("reciever");
	%>
	</p>
	<br>
	<br>
	<%
		String msg = "";
		//Message ms = new Message(null, msg, sender, reciever, , false);
	%>

	<br>
	<br>
	<button onclick="send()">Send</button>

</body>
</html>