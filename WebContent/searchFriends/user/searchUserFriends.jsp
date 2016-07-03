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
<form align="center" action="./searchFriendsServlet" method="post">
	Search Friends: <input type="text" name="username"
		placeholder="username"> <input type="submit" value="search" />
</form>



<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../../MenuFiles/RightSidebarNFooter.jsp"%>