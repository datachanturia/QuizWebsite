<%@ page import="ULS.AccountManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>
	<%
		AccountManager am = (AccountManager) request.getAttribute("accManager");
	%>
</title>
</head>
<body>
	<script>
		var auto_refresh = setInterval(function() {
			submitform();
		}, 0);

		function submitform() {
			document.myForm.submit();
		}
	</script>
	<form name="myForm" action="./adminLoggedIn/welcomeUser.jsp"
		method="GET">
		<input type="hidden" id="thisField" name="inputName"
			value="<%=am.getUser().getUsername()%>">
	</form>
</body>

</html>