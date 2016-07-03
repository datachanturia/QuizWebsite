<%@ page import="java.sql.SQLException" import="dataSrc.DataSource"
	import="java.sql.Connection" import="java.util.ArrayList"
	import="friends.friendsDatabaseConnector" import="ULS.AccountManager"
	import="Model.Quiz" import="java.util.ArrayList"
	import="MRC.MessageManager" import="Model.Message"%>

<%@ include file="../../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../../MenuFiles/SidebarMenu.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

<%
	String username = request.getParameter("username");
	String message = "No user with name '" + username + "' found...";
	if (username == null || username == "")
		message = "Please type username...";
%>

<form align="center" action="./searchFriendsServlet" method="post">
	Search Friends: <input type="text" name="username"
		placeholder="username"> <input type="submit" value="search" />
</form>

<h3 align="center">
	<%=message%></h3>

<h3 align="center">Please try again...</h3>

<!-- /col-lg-9 END SECTION MIDDLE -->



<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../../MenuFiles/RightSidebarNFooter.jsp"%>