<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="MRC.MessageManager"
	import="Model.Message"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%
		MessageManager mm = (MessageManager) request.getAttribute("MessManager");
		int len = 20;
		Message m = null;

		for (int i = 0; i < mm.getMessages().size(); i++) {
			m = mm.getMessages().get(i);
			String sender = m.getSenderName();
			String msg = m.getMessage();
			if (msg.length() > len) {
				msg = msg.substring(0, len) + "...";
			}
	%>
	<p>
		<%
			sender += "\t" + msg;
		%>
		<button onclick="readForm()">Read</button>
	</p>

	<br>
	<br>
	<%
		}
	%>
	<p>No More Messages</p>

	<form name="readForm" action="./MRC/Message.jsp" method="GET">
		<input type="hidden" id="message" name="message"
			value="<%=m.getMessage()%>"> <input type="hidden" id="sender"
			name="sender" value="<%=m.getSenderName()%>">
	</form>

</body>
</html>