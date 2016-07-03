<%@ page import="Model.Quiz" import="java.util.ArrayList"
	import="MRC.MessageManager" import="Model.Message"%>

<%@ include file="../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../MenuFiles/SidebarMenu.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

<div class="row mt">

	<form name="AllMessagesForm" action="ServletAllMessages" method="GET">
		<input type="submit" value="All Mails">
	</form>
	<br>
	<br>
	<p>New Messages:</p>
	<br>
	<br>
	<%
		MessageManager mm = (MessageManager) request.getAttribute("MessManager");
		ArrayList<Message> msgs = mm.myGetMessages();
		int userID = mm.getUserID();
		int len = 20;
		Message m = null;

		for (int i = 0; i < msgs.size(); i++) {
			m = msgs.get(i);
			if (m != null && m.getReceiverID() == userID && !m.isIsread()) {

				String sender = m.getSenderName();
				String msg = m.getMessage();
				String newOne = "New";
				if (msg.length() > len) {
					msg = msg.substring(0, len) + "...";
				}
	%>
	<p>
		<%
			out.println(sender);
		%>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			out.println(newOne);
		%>
		&nbsp; Message: &nbsp;
		<%
			out.println(msg);
		%>
		&nbsp;&nbsp;&nbsp;&nbsp;
	</p>

	<form name="readForm" action="ServletReadMesage" method="GET">
		<input type="hidden" id="message" name="message"
			value="<%=m.getMessage()%>"> <input type="hidden" id="sender"
			name="sender" value="<%=m.getSenderName()%>"> <input
			type="hidden" id="receiver" name="receiver"
			value="<%=m.getRecieverName()%>"> <input type="hidden"
			id="messageID" name="messageID" value="<%=m.getMessageID()%>">
		<input type="submit" value="Read">
	</form>
	<br> <br>


	<%
		}
		}
	%>
	<h5 class="centered">End Of Messages</h5>

</div>
<!-- /row -->


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>