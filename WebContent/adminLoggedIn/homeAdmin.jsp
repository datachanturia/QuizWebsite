<%@ page import="ULS.AccountManager" import="Model.Quiz"
	import="java.util.ArrayList"%>

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
	String hidden = request.getParameter("inputName");
	String fphoto = request.getParameter("fphoto");
	String usId = request.getParameter("usId");
	int newMsgs = 4;
	int newRequests = 5;

	// <<<<<<<<<<<<<<<<< day popular quizes start here
	ArrayList<String> idDPls = new ArrayList<String>();
	ArrayList<String> nmDPls = new ArrayList<String>();

	int DPSize = Integer.parseInt(request.getParameter("qzDPSize"));

	for (int i = 0; i < DPSize; i++) {
		nmDPls.add(request.getParameter("qzDPName" + i));
		idDPls.add(request.getParameter("qzDPId" + i));
	}
	// day popular quizes end here >>>>>>>>>>>>>>>>>>>>>>>>>

	// <<<<<<<<<<<<<<<<< popular quizes start here
	ArrayList<String> idPQls = new ArrayList<String>();
	ArrayList<String> nmPQls = new ArrayList<String>();

	int PQSize = Integer.parseInt(request.getParameter("qzPQSize"));

	for (int i = 0; i < PQSize; i++) {
		nmPQls.add(request.getParameter("qzPQName" + i));
		idPQls.add(request.getParameter("qzPQId" + i));
	}
	// popular quizes end here >>>>>>>>>>>>>>>>>>>>>>>>>

	// <<<<<<<<<<<<<<<<< new quizes start here
	ArrayList<String> idNQls = new ArrayList<String>();
	ArrayList<String> nmNQls = new ArrayList<String>();
	ArrayList<String> dtNQls = new ArrayList<String>();

	int NQSize = Integer.parseInt(request.getParameter("qzNQSize"));

	for (int i = 0; i < NQSize; i++) {
		nmNQls.add(request.getParameter("qzNQName" + i));
		idNQls.add(request.getParameter("qzNQId" + i));
		dtNQls.add(request.getParameter("qzNQDt" + i));
	}
	// new quizes end here >>>>>>>>>>>>>>>>>>>>>>>>>
%> <%=hidden%></title>



<!-- Bootstrap core CSS -->
<link href="../loggedIn/assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="../loggedIn/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="../loggedIn/assets/css/zabuto_calendar.css">
<link rel="stylesheet" type="text/css"
	href="../loggedIn/assets/js/gritter/css/jquery.gritter.css" />
<link rel="stylesheet" type="text/css"
	href="../loggedIn/assets/lineicons/style.css">

<!-- Custom styles for this template -->
<link href="../loggedIn/assets/css/style.css" rel="stylesheet">
<link href="../loggedIn/assets/css/style-responsive.css"
	rel="stylesheet">

<script src="../loggedIn/assets/js/chart-master/Chart.js"></script>

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
									out.println(newRequests);
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
									out.println(newMsgs);
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
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Write Post</span>
					</a>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Make User Admin By Id</span>
					</a>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Bann User By Id</span>
					</a>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Add Quiz</span>
					</a>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Remove Quiz</span>
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


						<div class="row mt">
							<!-- SERVER STATUS PANELS -->
							<div class="col-md-4 col-sm-4 mb">
								<div class="darkblue-panel pn">
									<div class="darkblue-header">
										<h5>TOP POPULAR QUIZES</h5>
									</div>
									<%
										for (int i = 0; i < PQSize; i++) {
											out.println("<h5>" + nmPQls.get(i) + " Quiz"
													+ " <button style=\"background-color:#555555\"> Take Quiz </button></h5>");
										}
									%>
								</div>
								<!-- /grey-panel -->
							</div>
							<!-- /col-md-4-->


							<div class="col-md-4 col-sm-4 mb">
								<div class="white-panel pn">
									<div class="white-header">
										<h5>TOP NEW QUIZES</h5>
									</div>
									<%
										for (int i = 0; i < NQSize; i++) {
											out.println("<h5> <" + dtNQls.get(i) + "> " + nmNQls.get(i) + " Quiz"
													+ " <button style=\"background-color:#555555\"> Take Quiz </button></h5>");
										}
									%>
								</div>
							</div>
							<!-- /col-md-4 -->

							<div class="col-md-4 mb">
								<!-- WHITE PANEL - TOP FRIENDS -->
								<div class="darkblue-panel pn">
									<div class="darkblue-header">
										<h5>QUIZES OF THE DAY</h5>
									</div>
									<%
										for (int i = 0; i < DPSize; i++) {
											out.println("<h5>" + nmDPls.get(i) + " Quiz"
													+ " <button style=\"background-color:#555555\"> Take Quiz </button></h5>");
										}
									%>
								</div>
							</div>
							<!-- /col-md-4 -->


						</div>
						<!-- /row -->

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
							for (int i = 0; i < 4; i++) {
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
							NEW MESSAGES <a href=""> -- see all</a>
						</h3>
						<!-- First Member -->
						<%
							for (int i = 0; i < 4; i++) {
						%>
						<div class="desc">
							<div class="thumb">
								<img class="img-circle"
									src="../loggedIn/assets/img/ui-divya.jpg" width="35px"
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
	<script src="../loggedIn/assets/js/jquery.js"></script>
	<script src="../loggedIn/assets/js/jquery-1.8.3.min.js"></script>
	<script src="../loggedIn/assets/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="../loggedIn/assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="../loggedIn/assets/js/jquery.scrollTo.min.js"></script>
	<script src="../loggedIn/assets/js/jquery.nicescroll.js"
		type="text/javascript"></script>
	<script src="../loggedIn/assets/js/jquery.sparkline.js"></script>


	<!--common script for all pages-->
	<script src="../loggedIn/assets/js/common-scripts.js"></script>

	<script type="text/javascript"
		src="../loggedIn/assets/js/gritter/js/jquery.gritter.js"></script>
	<script type="text/javascript"
		src="../loggedIn/assets/js/gritter-conf.js"></script>

	<!--script for this page-->
	<script src="../loggedIn/assets/js/sparkline-chart.js"></script>
	<script src="../loggedIn/assets/js/zabuto_calendar.js"></script>


</body>
</html>
