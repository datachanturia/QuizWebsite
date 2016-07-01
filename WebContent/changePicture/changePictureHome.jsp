<%@page import="changeProfilePicture.chProfilePicServlet"%>

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


<form align="center" action="../chProfilePicServlet" method="post">
	<H3 align="center">Change profile picture</H3>
	Type profile picture URL: <input type="text" name="picturePath"
		placeholder="picture URL"> <input type="submit" value="Done" />

	<input type="hidden" name="userID" value=1>
</form>

<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>
