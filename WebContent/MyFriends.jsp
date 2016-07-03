<%@ page import="ULS.AccountManager" import="Model.Quiz"
	import="Model.User" import="
	java.util.ArrayList"
	import="MRC.MessageManager" import="Model.Message"%>

<%@ include file="./MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="./MenuFiles/SidebarMenu.jsp"%>


<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

<%
	ArrayList<User> mfriends = (ArrayList<User>) request.getAttribute("mfriends");
	int sizee = mfriends.size() / 3;
	int ssize = 3;

	if (sizee < 1) {
		sizee = 1;
		ssize = mfriends.size();
	}
	for (int i = 0; i < sizee; i++) {
		if (i % 3 == 0) {
%>
<div class="row mt">
	<!-- SERVER STATUS PANELS -->
	<div class="center" align="center">
		<table border="1">
			<%
				for (int j = 0; j < ssize; j++) {
							int indexx = i * 3 + j;
			%>
			<form action="./ServletSendMessage" method="get"
				name="myForm<%=indexx%>">
				<input name="receiver" type="hidden"
					value="<%=Integer.toString(mfriends.get(indexx).getUserID())%>" />
				<input name="receiverName" type="hidden"
					value="<%=mfriends.get(indexx).getUsername()%>" />
			</form>

			<form action="./ServletToCab" method="get" name="myForm1<%=indexx%>">
				<input name="friendID" type="hidden"
					value="<%=Integer.toString(mfriends.get(indexx).getUserID())%>" />
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
					Message</a><br> <a href="#"
				onclick="document.forms['myForm<%=indexx%>'].submit()">Block
					Friend</a></td>

			<%
				}
			%>

		</table>
	</div>
</div>
<%
	}
	}
%>

<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>