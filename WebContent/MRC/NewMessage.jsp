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
	String receiverName = request.getParameter("receiverName");
%>
<p>
	To: &nbsp;
	<%
		out.println(receiverName);
	%>
</p>
<br>
<textarea rows=15, cols=70, id="myTextarea">
	</textarea>
<br>
<br>

<form name="sendForm" action="ServletSendingMessage" method="GET">
	<input type="hidden" name="message"
		value="<script>(String) document.getElementById('myTextarea').value </script>">
	<input type="submit" value="Send">
</form>


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>