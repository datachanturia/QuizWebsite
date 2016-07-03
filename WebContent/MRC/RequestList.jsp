<%@ page import="ULS.AccountManager" import="Model.Quiz"
	import="java.util.ArrayList" import="MRC.RequestManager"
	import="Model.Request" import="java.sql.Connection"
	import="Database.RequestDaoImpl" import = "java.sql.SQLException"%>

<%@ include file="../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../MenuFiles/SidebarMenu.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

<div class="row mt">

	<%
		RequestManager rm = (RequstManager) request.getAttribute("RequManager");
		ArrayList<Requests> req = rm.getRequests();
		int userID = rm.getUserID();
		Request r = null;

		for (int i = 0; i < req.size(); i++) {
			r = req.get(i);
			String sender = r.getSenderName();
			String requ = "wants to be your friend";
	%>
	<p>
		<%
			out.println(sender);
		%>
		&nbsp;
		<%
			out.println(req);
		%>
		&nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<%
		if (r != null) {
	%>
	<button type="button" onclick="accept()">Accept</button>
	<p>&nbsp;</p>
	<button type="button" onclick="reject()">Reject</button>
	<br> <br>

	<%
		}
		}
	%>
	<h5 class="centered">End Of Requests</h5>

	<script>
		function accept() {
			Date
			date = new Date();
			java.sql.Date
			sqlDate = new java.sql.Date(date.getTime());
			Connection con = null;
			try {
				con = DataSource.getInstance().getConnection();
				RequestDaoImpl rdi = new RequestDaoImpl(con);
				rdi.deleteRequest(userrID);
				// TODO add friend!!!
			} finally {
				if (con != null)
					try {
						con.close();
					} catch (SQLException ) {
						//e.printStackTrace();
					}
			}
		}
		
		function reject() {
			Date
			date = new Date();
			java.sql.Date
			sqlDate = new java.sql.Date(date.getTime());
			Connection con = null;
			try {
				con = DataSource.getInstance().getConnection();
				RequestDaoImpl rdi = new RequestDaoImpl(con);
				rdi.deleteRequest(userrID);
			} finally {
				if (con != null)
					try {
						con.close();
					} catch (SQLException ) {
						//e.printStackTrace();
					}
			}
		}
	</script>

</div>
<!-- /row -->


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>