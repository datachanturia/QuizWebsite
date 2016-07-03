<%@ page  import="Model.Quiz"
	import="java.util.ArrayList" import="MRC.MessageManager"
	import="Model.Message"%>

<%@ include file="./MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="./MenuFiles/SidebarMenu.jsp"%>


<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

<form align="center" action="./chPassServlet" method="POST">
	<H3 align="center">Change Password:</H3>
	Type in current password: <input name="curPas" type="password"
		placeholder="current password" id="curPas" required /><br> <br>
	Type new password: <input name="newPas1" type="password"
		placeholder="new password" id="newPas1" required /><br> <br>
	New password again:<input name="newPas2" type="password"
		placeholder="new password once more" id="newPas2" required /><br>
	<br>
	<button>Change Pass</button>
</form>


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>