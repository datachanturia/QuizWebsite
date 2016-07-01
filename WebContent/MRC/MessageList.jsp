<%@ page import="ULS.AccountManager" import="Model.Quiz"
	import="java.util.ArrayList" import="MRC.MessageManager"
	import="Model.Message"%>

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

	<br> <br>
	<%
		}
	%>
	<h5 class="centered">End Of Messages</h5>

	<%
		if (m != null) {
	%>
	<form name="readForm" action="./MRC/Message.jsp" method="GET">
		<input type="hidden" id="message" name="message"
			value="<%=m.getMessage()%>"> <input type="hidden" id="sender"
			name="sender" value="<%=m.getSenderName()%>">
	</form>
	<%
		}
	%>


</div>
<!-- /row -->


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>