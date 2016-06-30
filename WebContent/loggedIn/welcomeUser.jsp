<%@ page import="ULS.AccountManager" import="Model.Quiz"
	import="java.util.ArrayList"%>
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
	<form name="myForm" action="./loggedIn/homeUser.jsp" method="GET">
		<input type="hidden" id="thisField" name="inputName"
			value="<%=am.getUser().getUsername()%>"> <input type="hidden"
			id="fphoto" name="fphoto" value="<%=am.getUser().getPhoto()%>">
		<input type="hidden" id="usId" name="usId"
			value="<%=am.getUser().getUserID()%>">



		<!-- here goes day popular quizes!!!!!!!!!!!!!!!!!!!!!!!!! -->
		<%
			ArrayList<Quiz> qzDPls = (ArrayList<Quiz>) request.getAttribute("dayPopuLs");

			int sizeDP = 6;
			if (qzDPls.size() < 6)
				sizeDP = qzDPls.size();
		%>

		<input type="hidden" id="qzDPSize" name="qzDPSize" value="<%=sizeDP%>">

		<%
			for (int i = 0; i < sizeDP; i++) {
		%>
		<input type="hidden" id="<%="qzDPName" + i%>"
			name="<%="qzDPName" + i%>" value="<%=qzDPls.get(i).getQuizname()%>">
		<input type="hidden" id="<%="qzDPId" + i%>" name="<%="qzDPId" + i%>"
			value="<%=qzDPls.get(i).getQuizID()%>">
		<%
			}
		%>
		<!-- end of day popular quizes!!!!!!!!!!!!!!!!!!!!!!!!! -->


		<!-- here goes popular quizes!!!!!!!!!!!!!!!!!!!!!!!!! -->
		<%
			ArrayList<Quiz> qzPQls = (ArrayList<Quiz>) request.getAttribute("popQuizLs");

			int sizePQ = 6;
			if (qzPQls.size() < 6)
				sizePQ = qzPQls.size();
		%>

		<input type="hidden" id="qzPQSize" name="qzPQSize" value="<%=sizePQ%>">

		<%
			for (int i = 0; i < sizePQ; i++) {
		%>
		<input type="hidden" id="<%="qzPQName" + i%>"
			name="<%="qzPQName" + i%>" value="<%=qzPQls.get(i).getQuizname()%>">
		<input type="hidden" id="<%="qzPQId" + i%>" name="<%="qzPQId" + i%>"
			value="<%=qzPQls.get(i).getQuizID()%>">
		<%
			}
		%>
		<!-- end of popular quizes!!!!!!!!!!!!!!!!!!!!!!!!! -->

		<!-- here goes new quizes!!!!!!!!!!!!!!!!!!!!!!!!! -->
		<%
			ArrayList<Quiz> qzNQls = (ArrayList<Quiz>) request.getAttribute("newQuizLs");

			int sizeNQ = 6;
			if (qzNQls.size() < 6)
				sizeNQ = qzNQls.size();
		%>

		<input type="hidden" id="qzNQSize" name="qzNQSize" value="<%=sizeNQ%>">

		<%
			for (int i = 0; i < sizeNQ; i++) {
		%>
		<input type="hidden" id="<%="qzNQName" + i%>"
			name="<%="qzNQName" + i%>" value="<%=qzNQls.get(i).getQuizname()%>">
		<input type="hidden" id="<%="qzNQId" + i%>" name="<%="qzNQId" + i%>"
			value="<%=qzNQls.get(i).getQuizID()%>">
		<input type="hidden" id="<%="qzNQDt" + i%>" name="<%="qzNQDt" + i%>"
			value="<%=qzNQls.get(i).getCreationDate()%>">
		<%
			}
		%>
		<!-- end of new quizes!!!!!!!!!!!!!!!!!!!!!!!!! -->
	</form>
</body>

</html>