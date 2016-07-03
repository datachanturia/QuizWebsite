<%@page import="Model.Message"
	import="java.util.Date" import="MRC.MessageManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../MenuFiles/SidebarMenu.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->


<%
	String sender = request.getParameter("sender");
	MessageManager mm = (MessageManager) request.getAttribute("MessManager");
%>
<p>
	To:
	<%=request.getAttribute("receiverName")%>
	<%
		String reciever = request.getParameter("receiver");
	%>
</p>
<br>
<textarea rows=15, cols=70, id="myTextarea">
	</textarea>
<br>
<br>
<button type="button" onclick="send()">Send</button>

<script>
	function send() {
		var x = document.getElementById("myTextarea").value;
		String
		msg = (String)
		x;
		Date
		date = new Date();
		java.sql.Date
		sqlDate = new java.sql.Date(date.getTime());
		Message
		ms = new Message(0, msg, sender, reciever, sqlDate, false);
		mm.sendMessage(ms);
	}
</script>

<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>