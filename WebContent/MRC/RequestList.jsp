<%@ page import="ULS.AccountManager" import="Model.Quiz"
	import="java.util.ArrayList" import="MRC.RequestManager"
	import="Model.Request" import="java.sql.Connection"
	import="Database.RequestDaoImpl" import="java.sql.SQLException"%>

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
		RequestManager rm = (RequestManager) request.getAttribute("RequManager");
		ArrayList<Request> req = rm.getRequests();
		int userID = rm.getUserID();
		Request r = null;

		for (int i = 0; i < req.size(); i++) {
			r = req.get(i);
			String sender = r.getSenderName();
			String requ = "wants to be your friend";
	%>
	<p>
		&nbsp;
		<%
			out.println(sender);
		%>
		&nbsp;
		<%
			out.println(requ);
		%>
		&nbsp;&nbsp;
	</p>
	<%
		if (r != null) {
	%>
	<form name="acceptForm" action="ServletAcceptRequest" method="GET">
		<input type="hidden" id="requestID" name="requestID"
			value="<%=r.getRequestID()%>"> <input type="hidden"
			id="sender" name="sender" value="<%=r.getSenderID()%>"> <input
			type="hidden" id="receiver" name="receiver"
			value="<%=r.getReceiverID()%>"> <input type="submit"
			value="accept">
	</form>

	<form name="rejectForm" action="ServletRejectRequest" method="GET">
		<input type="hidden" id="requestID" name="requestID"
			value="<%=r.getRequestID()%>"> <input type="submit"
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