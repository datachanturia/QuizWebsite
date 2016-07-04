<%@page import="java.util.Date"%>
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
	int sender = (int) request.getAttribute("sender");
	int receiver = Integer.parseInt(request.getParameter("receiver"));
	String receiverName = request.getParameter("receiverName");
%>
<p>
	To: &nbsp;
	<%
		out.println(receiverName);
	%>
</p>
<br>


<form name="sendForm" action="ServletSendingMessage" method="GET">
	<input type="hidden" name="sender" value=<%=sender%>> <input
		type="hidden" name="receiver" value=<%=receiver%>>
	<textarea rows=15, cols=70, name="message"> </textarea>
	<br> <br> <input type="submit" value="Send">
</form>


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>