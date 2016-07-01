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
<!--main content start-->
<section id="main-content">
	<section class="wrapper">

		<div class="row">
			<div class="col-lg-9 main-chart">

				<form align="center" action="./searchFriendsServlet" method="post">
					Search Friends: <input type="text" name="username"
						placeholder="username"> <input type="submit"
						value="search" />
				</form>
				<h4 align="center">My Friends</h4>


				<div class="row mt">
					<!-- SERVER STATUS PANELS -->
					<div class="center" align="center">
						<table border="1">

							<%
								
								Connection con;
								con = DataSource.getInstance().getConnection();
								friendsDatabaseConnector frc = new friendsDatabaseConnector(con);
								frc.setConnection(con);
								ArrayList <Integer> friends = (ArrayList<Integer>) request.getAttribute("users");
								int numberOfFriends = friends.size();
							%>

							<%
								int written = 0;
								for (int j = 0; j < numberOfFriends / 3 + numberOfFriends % 3; j++) {
							%>

							<tr>
								<%
									for (int k = 0; k < 3 && i < numberOfFriends; k++, written++) {
								%>
								<td bgcolor="#444c57" height="200" width="175" valign="top"
									align="center">
									<%
										String userPhoto = frc.getUserPhoto(friends.get(j * 2 + k));
												String userName = frc.getUserName(friends.get(j * 2 + k));
									%>
									<div class="darkblue-panel pn">
										<img src="<%=userPhoto%>" vspace="10" width=80 height=80
											class="img-circle"></img>

										<p align="center">
											<font size="+1.5" color="#ffebbb"> <%=userName%></font>
										<h5>
											<button style="background-color: #555555">Add Friend</button>
										</h5>
										<h5>
											<button style="background-color: #555555">Challenge
												Friend</button>
										</h5>
										<h5>
											<button style="background-color: #555555">Send
												Message</button>
										</h5>
										</p>
									</div>
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


			</div>
			<!-- /col-lg-9 END SECTION MIDDLE -->


			<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

			<%@ include file="../../MenuFiles/RightSidebarNFooter.jsp"%>