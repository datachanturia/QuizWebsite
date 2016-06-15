<%@ page import="ULS.AccountManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>
	Home <%
		String hidden = request.getParameter("inputName");
	%> <%=hidden%>
</title>
</head>
<body>
	<h1>
		Welcome 
		<%=hidden%></h1>
</body>
</html>