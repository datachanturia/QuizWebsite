<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>
	<% String name = request.getParameter("username"); 
	out.println("Welcome " + name); %>
</title>
</head>
<body>
	<% out.println("<h1>Welcome " + name + "</h1>"); %>
</body>
</html>