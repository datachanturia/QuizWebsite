<%@ page import="java.sql.SQLException" import="dataSrc.DataSource"
	import="java.sql.Connection" import="java.util.ArrayList"
	import="friends.friendsDatabaseConnector"
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
<h4 align="center">Search Result</h4>


<div class="row mt">
	<!-- SERVER STATUS PANELS -->
	<div class="center" align="center">
		<table border="1">

			<%
				Connection con;
				con = DataSource.getInstance().getConnection();
				friendsDatabaseConnector frc = new friendsDatabaseConnector(con);
				frc.setConnection(con);

				ArrayList<Integer> friends = (ArrayList<Integer>) request.getAttribute("users");
				int numberOfFriends = friends.size();
			%>

			<%
				int i = 0;
				for (int j = 0; j < numberOfFriends / 3 + numberOfFriends % 3; j++) {
			%>

			<tr>
				<%
					for (int k = 0; k < 3 && i < numberOfFriends; k++, i++) {
				%>
				<td bgcolor="#444c57" height="200" width="175" valign="top"
					align="center">
					<%
						String userPhoto = frc.getUserPhoto(friends.get(j * 2 + k));
								String userName = frc.getUserName(friends.get(j * 2 + k));
					%>
					<form action="./ServletToCab" method="get"
						name="myForm1<%=j * 2 + k%>">
						<input name="friendID" type="hidden"
							value="<%=Integer.toString(friends.get(j * 2 + k))%>" />
					</form> <a href="#"
					onclick="document.forms['myForm1<%=j * 2 + k%>'].submit()"\> <img
						src="<%=userPhoto%>" vspace="10" width=80 height=80
						class="img-circle"></img>
				</a>
					<p align="center">
						<font size="+1.5" color="#ffebbb"> <%=userName%></font>
					</p>
				</td>
				<%
					}
				%>
			</tr>
			<%
				}
				if (con != null)
					try {
						con.close();
					} catch (SQLException e) {
					}
			%>

		</table>
	</div>

	<!-- /row -->
</div>


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../../MenuFiles/RightSidebarNFooter.jsp"%>