<%@page import="Model.Message" import="java.util.Date"
	import="MRC.MessageManager"%>
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
		String sender = request.getParameter("sender");
		MessageManager mm = (MessageManager) request.getAttribute("MessManager");
	%>
	<p>
		To: &nbsp;
		<%
			String reciever = request.getParameter("receiver");
		%>
	</p>
	<br>
	<textarea rows=15, cols=70, id="myTextarea">
	</textarea>
	<br>
	<br>
	<button type="button" onclick="send()">Send</button>

	<script>
		function send() {
			var x = document.getElementById("myTextarea").value;
			String
			msg = (String)
			x;
			Date
			date = new Date();
			java.sql.Date
			sqlDate = new java.sql.Date(date.getTime());
			Message
			ms = new Message(0, msg, sender, reciever, sqlDate, false);
			mm.sendMessage(ms);
		}
	</script>

</body>
</html>