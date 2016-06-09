<%@ page import="ULS.AccountManager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>
	<% AccountManager am = (AccountManager)request.getAttribute("accManager");
	out.println("Welcome " + am.getUser().getUsername()); %>
</title>
</head>
<body>
	<% out.println("<h1>Welcome " + am.getUser().getUsername() + "</h1>"); %>
</body>
</html>