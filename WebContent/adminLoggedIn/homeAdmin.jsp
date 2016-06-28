<%@ page import="ULS.AccountManager"%>

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
%> <%=hidden%></title>



<!-- Bootstrap core CSS -->
<link href="../loggedIn/assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="../loggedIn/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="../loggedIn/assets/css/zabuto_calendar.css">
<link rel="stylesheet" type="text/css"
	href="../loggedIn/assets/js/gritter/css/jquery.gritter.css" />
<link rel="stylesheet" type="text/css" href="../loggedIn/assets/lineicons/style.css">

<!-- Custom styles for this template -->
<link href="../loggedIn/assets/css/style.css" rel="stylesheet">
<link href="../loggedIn/assets/css/style-responsive.css" rel="stylesheet">

<script src="../loggedIn/assets/js/chart-master/Chart.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
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
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle" href="index.html#"> <i
							class="fa fa-tasks"></i> <span class="badge bg-theme"> <%
 	out.println(newRequests);
 %>
						</span>
					</a>
						<ul class="dropdown-menu extended tasks-bar">
							<li>
								<p class="green">
									You have
									<%
									out.println(newRequests);
								%>
									pending requests
								</p>
							</li>
							<%
								for (int i = 0; i < newRequests; i++) {
							%>
							<li><a href="index.html#"> </a></li>
							<%
								}
							%>
							<li class="external"><a href="#">See All Requests</a></li>
						</ul></li>
					<!-- settings end -->
					<!-- inbox dropdown start-->
					<li id="header_inbox_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
							<i class="fa fa-envelope-o"></i> <span class="badge bg-theme">
								<%
									out.println(newMsgs);
								%>
						</span>
					</a>
						<ul class="dropdown-menu extended inbox">

							<li>
								<p class="green">
									You have
									<%
									out.println(newMsgs);
								%>
									new messages
								</p>
							</li>
							<%
								for (int i = 0; i < newMsgs; i++) {
							%>
							<li><a href="index.html#"> <span class="photo"><img
										alt="avatar" src="../loggedIn/assets/img/ui-zac.jpg"></span> <span
									class="subject"> <span class="from">Zac Snider</span> <span
										class="time">Just now</span>
								</span> <span class="message"> Hi mate, how is everything? </span>
							</a></li>
							<%
								}
							%>
							<li><a href="index.html#">See all messages</a></li>
						</ul></li>
					<!-- inbox dropdown end -->
				</ul>
				<!--  notification end -->
			</div>
			<div class="top-menu">
				<ul class="nav pull-right top-menu">
					<li><a class="logout" href="../logMenu/index.html">Logout</a></li>
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
										<h5>TOP 5 POPULAR QUIZES</h5>
									</div>
								</div>
								<!-- /grey-panel -->
							</div>
							<!-- /col-md-4-->


							<div class="col-md-4 col-sm-4 mb">
								<div class="white-panel pn">
									<div class="white-header">
										<h5>TOP 5 NEW QUIZES</h5>
									</div>
								</div>
							</div>
							<!-- /col-md-4 -->

							<div class="col-md-4 mb">
								<!-- WHITE PANEL - TOP FRIENDS -->
								<div class="darkblue-panel pn">
									<div class="darkblue-header">
										<h5>QUIZES OF THE DAY</h5>
									</div>
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
								<img class="img-circle" src="../loggedIn/assets/img/ui-divya.jpg"
									width="35px" height="35px">
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
	<script src="../loggedIn/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="../loggedIn/assets/js/jquery.sparkline.js"></script>


	<!--common script for all pages-->
	<script src="../loggedIn/assets/js/common-scripts.js"></script>

	<script type="text/javascript"
		src="../loggedIn/assets/js/gritter/js/jquery.gritter.js"></script>
	<script type="text/javascript" src="../loggedIn/assets/js/gritter-conf.js"></script>

	<!--script for this page-->
	<script src="../loggedIn/assets/js/sparkline-chart.js"></script>
	<script src="../loggedIn/assets/js/zabuto_calendar.js"></script>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							var unique_id = $.gritter
									.add({
										// (string | mandatory) the heading of the notification
										title : 'Welcome to Dashgum!',
										// (string | mandatory) the text inside the notification
										text : 'Hover me to enable the Close Button. You can hide the left sidebar clicking on the button next to the logo. Free version for <a href="http://blacktie.co" target="_blank" style="color:#ffd777">BlackTie.co</a>.',
										// (string | optional) the image to display on the left
										image : '../loggedIn/assets/img/ui-sam.jpg',
										// (bool | optional) if you want it to fade out on its own or just sit there
										sticky : true,
										// (int | optional) the time you want it to be alive for before fading out
										time : '',
										// (string | optional) the class name you want to apply to that specific message
										class_name : 'my-sticky-class'
									});

							return false;
						});
	</script>

	<script type="application/javascript">
		
		
		
		
		
		
		
		
		
		
        $(document).ready(function () {
            $("#date-popover").popover({html: true, trigger: "manual"});
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
                $(this).hide();
            });
        
            $("#my-calendar").zabuto_calendar({
                action: function () {
                    return myDateFunction(this.id, false);
                },
                action_nav: function () {
                    return myNavFunction(this.id);
                },
                ajax: {
                    url: "show_data.php?action=1",
                    modal: true
                },
                legend: [
                    {type: "text", label: "Special event", badge: "00"},
                    {type: "block", label: "Regular event", }
                ]
            });
        });
        
        
        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        }
    
	
	
	
	
	
	
	
	
	
	</script>


</body>
</html>
