<%@page import="java.sql.SQLException"%>
<%@page import="dataSrc.DataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="friends.friendsDatabaseConnector"%>
<%@ page import="ULS.AccountManager" import="Model.Quiz"
	import="java.util.ArrayList" import="MRC.MessageManager"
	import="Model.Message"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>Home <%
	ArrayList<Integer> friends = (ArrayList<Integer>) request.getAttribute("users");
	int numberOfFriends = friends.size();

	AccountManager am = (AccountManager) request.getAttribute("accManager");

	String hidden = am.getUser().getUsername();
	String fphoto = am.getUser().getPhoto();
	String usId = Integer.toString(am.getUser().getUserID());

	String isAdmin = Boolean.toString(am.getUser().isAdmin());

	int newMsgs = 4;
	int newRequests = 5;
%> <%=hidden%></title>



<!-- Bootstrap core CSS -->
<link href="./loggedIn/assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="./loggedIn/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="./loggedIn/assets/css/zabuto_calendar.css">
<link rel="stylesheet" type="text/css"
	href="./loggedIn/assets/js/gritter/css/jquery.gritter.css" />
<link rel="stylesheet" type="text/css"
	href="./loggedIn/assets/lineicons/style.css">

<!-- Custom styles for this template -->
<link href="./loggedIn/assets/css/style.css" rel="stylesheet">
<link href="./loggedIn/assets/css/style-responsive.css"
	rel="stylesheet">

<script src="./loggedIn/assets/js/chart-master/Chart.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<script>
		function submitform() {
			document.myForm.submit();
		}

		function submitMessage() {
			document.forMessage.submit();
		}

		function submitChallenge() {
			document.forChallenge.submit();
		}

		function submitRequest() {
			document.forRequest.submit();
		}
	</script>
	<form name="myForm" action="" method="GET">
		<input type="hidden" id="thisField" name="inputName"
			value="<%=hidden%>"> <input type="hidden" id="fphoto"
			name="fphoto" value="<%=fphoto%>"> <input type="hidden"
			id="usId" name="usId" value="<%=usId%>">
	</form>

	<form name="forMessage" action="../ServletMessage" method="GET">
		<input type="hidden" id="usId" name="usId" value="<%=usId%>">
	</form>

	<form name="forChallenge" action="../ServletChallenge" method="GET">
		<input type="hidden" id="usId" name="usId" value="<%=usId%>">
	</form>

	<form name="forRequest" action="../ServletRequest" method="GET">
		<input type="hidden" id="usId" name="usId" value="<%=usId%>">
	</form>

	<section id="container">
		<!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
		<!--header start-->
		<header class="header black-bg">
			<div class="sidebar-toggle-box">
				<div class="fa fa-bars tooltips" data-placement="right"
					data-original-title="Toggle Navigation"></div>
			</div>
			<!--logo start-->


			<a href="" class="logo"><b>QUIZ WEBSITE</b></a>

			<!--logo end-->
			<div class="nav notify-row" id="top_menu">
				<!--  notification start -->
				<ul class="nav top-menu">
					<!-- settings start -->
					<li class="dropdown"><a onclick="submitRequest()"
						data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
							<i class="fa fa-tasks"></i> <span class="badge bg-theme">
								<%
									//	out.println(newRequests);
								%>
						</span>
					</a></li>
					<!-- settings end -->
					<!-- inbox dropdown start-->
					<li id="header_inbox_bar" class="dropdown"><a
						onclick="submitMessage()" data-toggle="dropdown"
						class="dropdown-toggle" href="index.html#"> <i
							class="fa fa-envelope-o"></i> <span class="badge bg-theme">
								<%
									//	out.println(newMsgs);
								%>
						</span>
					</a></li>
					<!-- inbox dropdown end -->
				</ul>
				<!--  notification end -->
			</div>
			<div class="top-menu">
				<ul class="nav pull-right top-menu">
					<li><a class="logout" href="../index.jsp">Logout</a></li>
				</ul>
			</div>
		</header>
		<!--header end-->

		<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
		<!--sidebar start-->
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<!-- sidebar menu start-->
				<ul class="sidebar-menu" id="nav-accordion">

					<p class="centered">
						<a><img src="<%=fphoto%>" class="img-circle" width="60"></img></a>
					</p>
					<h5 class="centered">
						<%=hidden%>
					</h5>

					<li class="sub-menu"><a href=""> <i class="fa fa-book"></i>
							<span>Home Page</span>
					</a>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Create Quiz</span>
					</a>
					<li class="sub-menu"><a href="javascript:;"> <i class="fa"></i>
							<span>My Friends</span>
					</a>
					<li class="sub-menu"><a href="javascript:;"> <i class="fa"></i>
							<span>Search Friends</span>
					</a>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Search Quizes</span>
					</a>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Change Password</span>
					</a>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Change Profile Picture</span>
					</a>
				</ul>
				<!-- sidebar menu end-->
			</div>
		</aside>
		<!--sidebar end-->

		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">

				<div class="row">
					<div class="col-lg-9 main-chart">

						<form align="center" action="./searchFriendsServlet"
							method="post">
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
											%> <img src="<%=userPhoto%>" vspace="10" width=80 height=80
											class="img-circle"></img>
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


					</div>
					<!-- /col-lg-9 END SECTION MIDDLE -->


					<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->

					<div class="col-lg-3 ds">
						<!--COMPLETED ACTIONS DONUTS CHART-->
						<h3>
							ADMIN POSTS <a href=""> -- see all</a>
						</h3>

						<!-- First Action -->
						<%
							for (i = 0; i < 4; i++) {
						%>
						<div class="desc">
							<div class="thumb">
								<span><i class="fa"></i></span>
							</div>
							<div class="details">
								<p>
									<br /> <a href="#">James Brown</a> subscribed to your
									newsletter.<br />
								</p>
							</div>
						</div>
						<%
							}
						%>

						<!-- USERS ONLINE SECTION -->
						<h3>
							NEW CHALLENGES <a href=""> -- see all</a>
						</h3>
						<!-- First Member -->
						<%
							for (i = 0; i < 4; i++) {
						%>
						<div class="desc">
							<div class="thumb">
								<img class="img-circle"
									src="./loggedIn/assets/img/ui-divya.jpg" width="35px"
									height="35px">
							</div>
							<div class="details">
								<p>
									<a href="#">DIVYA MANIAN</a><br />
								</p>
							</div>
						</div>
						<%
							}
						%>
					</div>
					<!-- /col-lg-3 -->
				</div>
				<!--/row -->
			</section>
		</section>

		<!--main content end-->
		<!--footer start-->
		<footer class="site-footer">
			<div class="text-center">
				2016 - www.freeuni.edu.ge - QUIZ WEBSITE <a href="" class="go-top">
					<i class="fa fa-angle-up"></i>
				</a>
			</div>
		</footer>
		<!--footer end-->
	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="./loggedIn/assets/js/jquery.js"></script>
	<script src="./loggedIn/assets/js/jquery-1.8.3.min.js"></script>
	<script src="./loggedIn/assets/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="./loggedIn/assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="./loggedIn/assets/js/jquery.scrollTo.min.js"></script>
	<script src="./loggedIn/assets/js/jquery.nicescroll.js"
		type="text/javascript"></script>
	<script src="./loggedIn/assets/js/jquery.sparkline.js"></script>


	<!--common script for all pages-->
	<script src="./loggedIn/assets/js/common-scripts.js"></script>

	<script type="text/javascript"
		src="./loggedIn/assets/js/gritter/js/jquery.gritter.js"></script>
	<script type="text/javascript"
		src="./loggedIn/assets/js/gritter-conf.js"></script>

	<!--script for this page-->
	<script src="./loggedIn/assets/js/sparkline-chart.js"></script>
	<script src="./loggedIn/assets/js/zabuto_calendar.js"></script>
</body>
</html>
