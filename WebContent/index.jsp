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




<!-- Bootstrap core CSS -->
<link href="./loggedIn/assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="./loggedIn/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="./loggedIn/assets/css/style.css" rel="stylesheet">

<script src="./loggedIn/assets/js/chart-master/Chart.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<%
		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");
		if (am != null && am.getLogStatus()) {
	%>
	<script type="text/javascript">
		window.onload = function() {
			var auto = setTimeout(function() {
				submitform();
			}, 0);

			function submitform() {
				document.forms["forLogIn"].submit();
			}
		}
	</script>

	<form name="forLogIn" action="./loggedInServlet" method="GET"></form>
	<%
		}
	%>

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
			<div class="top-menu">
				<ul class="nav pull-right top-menu">
					<li><a class="logout" href="./logMenu/index.html">Log In</a></li>
				</ul>
			</div>
			<div class="top-menu">
				<ul class="nav pull-right top-menu">
					<li><a class="logout" href="./logMenu/newAccount.html">Sign
							Up</a></li>
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

					<li class="sub-menu"><a href=""> <i class="fa fa-book"></i>
							<span>Home Page</span>
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
									<br> <br> <br>
									<h5>YOU NEED TO LOGIN OR SIGN UP</h5>
								</div>
								<!-- /grey-panel -->
							</div>
							<!-- /col-md-4-->


							<div class="col-md-4 col-sm-4 mb">
								<div class="white-panel pn">
									<div class="white-header">
										<h5>TOP NEW QUIZES</h5>
									</div>
									<br> <br> <br>
									<h5>YOU NEED TO LOGIN OR SIGN UP</h5>
								</div>
							</div>
							<!-- /col-md-4 -->

							<div class="col-md-4 mb">
								<!-- WHITE PANEL - TOP FRIENDS -->
								<div class="darkblue-panel pn">
									<div class="darkblue-header">
										<h5>QUIZES OF THE DAY</h5>
									</div>
									<br> <br> <br>
									<h5>YOU NEED TO LOGIN OR SIGN UP</h5>
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
						<!-- Admin Posts Start -->
						<h3>ADMIN POSTS</h3>

						<%
							java.util.ArrayList<Model.Post> posts = new java.util.ArrayList<Model.Post>();
							java.sql.Connection conn = null;
							try {
								conn = dataSrc.DataSource.getInstance().getConnection();
								Database.PostDaoImpl pdi = new Database.PostDaoImpl(conn);

								posts = pdi.getPosts();

							} finally {
								if (conn != null)
									try {
										conn.close();
									} catch (java.sql.SQLException e) {
										e.printStackTrace();
									}
							}
						%>
						<%
							for (int ii = 0; ii<posts.size() && ii < 7; ii++) {
						%>
						<div class="desc">
							<div class="thumb">
								<span><i class="fa"></i></span>
							</div>
							<div class="details">
								<p>
									<br /> <a href="#"><%=posts.get(ii).getAdminName()%>: </a>
									<%=posts.get(ii).getPost()%><br />
								</p>
							</div>
						</div>
						<%
							}
						%>
						<!-- Admin Posts End -->


					</div>
					<!-- /col-lg-3 -->
				</div>
				<!--/row -->
			</section>
		</section>

		<!--main content end-->
		
		<!--footer start
		<footer class="site-footer">
			<div class="text-center">
				2016 - www.freeuni.edu.ge - QUIZ WEBSITE <a href="" class="go-top">
					<i class="fa fa-angle-up"></i>
				</a>
			</div>
		</footer>
		footer end-->
	</section>
</body>
</html>
