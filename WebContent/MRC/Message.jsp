<%@ page import="Model.Quiz"
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

<%
	String sender = request.getParameter("sender");
	String receiver = request.getParameter("receiver");
	String msg = request.getParameter("message");
%>
<p>
	From: &nbsp;
	<%
		out.println(sender);
	%>
</p>
<br>
<p>
	To: &nbsp;
	<%
		out.println(receiver);
	%>
</p>
<br>
<br>
<br>
<p>
	<%
		out.println(msg);
	%>
</p>


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>