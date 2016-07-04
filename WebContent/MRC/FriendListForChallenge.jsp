<%@ page import="Model.Quiz" import="Model.User"
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
	int quizID = Integer.parseInt(request.getParameter("quizID"));
	ArrayList<User> mfriends = (ArrayList<User>) request.getAttribute("mfriends");
	int sizee = mfriends.size() / 3;
	if (mfriends.size() % 3 != 0)
		sizee++;
	int usersPerRow = 3;
	int indexx = 0;
	for (int i = 0; i < sizee; i++) {
%>
<div class="row mt">
	<!-- SERVER STATUS PANELS -->
	<div class="center" align="center">
		<table border="1">
			<%
				for (int j = 0; j < usersPerRow && indexx < mfriends.size(); j++, indexx++) {
			%>
			<form action="./ServletSendChallenge" method="get" name="myForm<%=indexx%>">
				<input name="receiverID" type="hidden"
					value="<%=Integer.toString(mfriends.get(indexx).getUserID())%>" />
					<input name="quizID" type="hidden"
					value="<%=quizID%>" />
			</form>

			<td bgcolor="#444c57" height="200" width="175" valign="top"
				align="center"><a href="#"
				onclick="document.forms['myForm1<%=indexx%>'].submit()"\> <img
					src="<%=mfriends.get(indexx).getPhoto()%>" vspace="10" width=80
					height=80 class="img-circle"></img>
			</a>
				<p align="center">
					<font size="+1.5" color="#ffebbb"> <%=mfriends.get(indexx).getUsername()%></font>
				</p> <a href="#" onclick="document.forms['myForm<%=indexx%>'].submit()">Send 
					Challenge</a><br></td>
			<%
				}
			%>

		</table>
	</div>
</div>
<%
	}
%>

<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>