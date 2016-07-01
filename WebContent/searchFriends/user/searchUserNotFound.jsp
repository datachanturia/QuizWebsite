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

				<h3 align="center">
					<%=message%></h3>
				<h3 align="center">Please try again...</h3>

			</div>
			<!-- /col-lg-9 END SECTION MIDDLE -->



			<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

			<%@ include file="../../MenuFiles/RightSidebarNFooter.jsp"%>