<%@ page import="Model.Quiz" import="java.util.ArrayList"
	import="MRC.ChallengeManager" import="Model.Challenge"
	import="java.sql.Connection" import="Database.RequestDaoImpl"
	import="java.sql.SQLException"%>

<%@ include file="../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../MenuFiles/SidebarMenu.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
<style type="text/css">
form {
	float: left;
}
</style>
<div class="row mt">

	<%
		ChallengeManager cm = (ChallengeManager) request.getAttribute("ChallManager");
		ArrayList<Challenge> chal = cm.getChallenges();
		int userID = cm.getUserID();
		Challenge c = null;
		String txt = "wants to challenge you in quiz:";
		String quizName = "";

		for (int i = 0; i < chal.size(); i++) {
			c = chal.get(i);
			String sender = c.getSenderName();
			quizName = c.getQuizName();
	%>
	<p>
		&nbsp;
		<%
			out.println(sender);
		%>
		&nbsp;
		<%
			out.println(txt);
		%>
		&nbsp;
		<%
			out.println(quizName);
		%>
		&nbsp;&nbsp;
	</p>
	<%
		if (c != null) {
	%>
	<form name="acceptForm" action="ServletAcceptChallenge" method="GET">
		<input type="hidden" id="challengerID" name="challengerID"
			value=<%=c.getSenderID()%>> <input type="hidden" id="quizID"
			name="quizID" value=<%=c.getQuizID()%>> <input type="hidden"
			id="challengeID" name="challengeID" value=<%=c.getChallengeID()%>>
		<input type="submit" value="accept">
	</form>

	<form name="rejectForm" action="ServletRejectChallenge" method="GET">
		<input type="hidden" id="challengeID" name="challengeID"
			value="<%=c.getChallengeID()%>"> <input type="submit"
			value="reject">
	</form>

	<br> <br>

	<%
		}
		}
	%>
	<h5 class="centered">End Of Requests</h5>



</div>
<!-- /row -->


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>