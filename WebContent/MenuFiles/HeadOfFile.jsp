
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">

<title>Quiz Website <%
	AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");

	String hidden = am.getUser().getUsername();
	String fphoto = am.getUser().getPhoto();
	String usId = Integer.toString(am.getUser().getUserID());

	String isAdmin = Boolean.toString(am.getUser().isAdmin());
	
	int newMsgs = 4;
	int newRequests = 5;
	
	
%></title>



<!-- Bootstrap core CSS -->
<link href="./loggedIn/assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="./loggedIn/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="./loggedIn/assets/css/style.css" rel="stylesheet">

</head>
<body>
	<section>
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

			<div class="nav notify-row">

				<!--  notification start -->
				<ul class="nav top-menu">

					<!-- settings start -->
					<li><a href="./ServletRequest"> <i class="fa fa-tasks"></i>
							<span class="badge bg-theme"> <%=newRequests%>
						</span>
					</a></li>
					<!-- settings end -->

					<!-- inbox dropdown start-->
					<li id="header_inbox_bar"><a href="./ServletMessage"> <i
							class="fa fa-envelope-o"></i> <span class="badge bg-theme">
								<%=newMsgs%>
						</span>
					</a></li>
					<!-- inbox dropdown end -->

				</ul>
				<!--  notification end -->

			</div>

			<div class="top-menu">
				<ul class="nav pull-right top-menu">
					<li><a class="logout" href="./loggedOutServlet">Logout</a></li>
				</ul>
			</div>
		</header>
		<!--header end-->