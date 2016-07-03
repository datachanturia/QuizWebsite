<%@page import="changeProfilePicture.chProfilePicServlet"%>

<%@page import="Model.Quiz" import="java.util.ArrayList"
	import="MRC.MessageManager" import="Model.Message"%>

<%@ include file="../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../MenuFiles/SidebarMenu.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->


<form align="center" action="./chProfilePicServlet" method="POST">
	<H3 align="center">Change profile picture</H3>
	Type profile picture URL: <input name="picturePath" type="text"
		placeholder="picture URL" id="picturePath" required />
	<button>Done</button>
</form>

<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>
