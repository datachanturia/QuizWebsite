<%@ page import="ULS.AccountManager" import="Model.Quiz"
	import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>
	<%
		AccountManager am = (AccountManager) request.getAttribute("accManager");
		ArrayList<Quiz> qzls = (ArrayList<Quiz>) request.getAttribute("dayPopuLs");
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
	<form name="myForm" action="./loggedIn/homeUser.jsp" method="GET">
		<input type="hidden" id="thisField" name="inputName"
			value="<%=am.getUser().getUsername()%>"> <input type="hidden"
			id="fphoto" name="fphoto" value="<%=am.getUser().getPhoto()%>">
		<input type="hidden" id="usId" name="usId"
			value="<%=am.getUser().getUserID()%>"> 
			
			
		<input type="hidden"
			id="<%out.print("quizSize");%>"
			name="
			<%out.print("quizSize");%>" value="<%=qzls.size()%>">

		<%
			int size = 10;
			if (qzls.size() < 10)
				size = qzls.size();

			for (int i = 0; i < size; i++) {
		%>


		<input type="hidden"
			id="<%out.print("quizSize" + i);%>"
			name="
			<%out.print("quizSize" + i);%>" value="<%=qzls.get(i).getQuizname()%>">
		
		<%
			}
		%>
	</form>
</body>

</html>