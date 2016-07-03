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
		ArrayList<Message> msgs = mm.myGetMessages();
		int userID = mm.getUserID();
		int len = 20;
		Message m = null;

		for (int i = 0; i < msgs.size(); i++) {
			m = msgs.get(i);
			String sender = m.getSenderName();
			String msg = m.getMessage();
			String newOne = "";
			if (msg.length() > len) {
				msg = msg.substring(0, len) + "...";
			}
			if (m.getSenderID() != userID && !m.isIsread()){
				newOne += "New";
			}
				
	%>
	<p>
		 <% out.println(sender); %>
		 &nbsp;&nbsp;&nbsp;&nbsp;
		 <% out.println(newOne); %>
		 &nbsp;
		 Message: &nbsp;
		 <% out.println(msg); %>
		 &nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<%
		if (m != null) {
	%>
	<form name="readForm" action="ServletReadMesage" method="GET">
		<input type="hidden" id="message" name="message"
			value="<%=m.getMessage()%>"> <input type="hidden" id="sender"
			name="sender" value="<%=m.getSenderName()%>"> <input type="hidden" 
			id="receiver" name="receiver" value="<%=m.getRecieverName()%>">
			<input type="hidden" id="messageID" name="messageID" value="<%=m.getMessageID()%>">
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