<%@ page import="ULS.AccountManager" import="Model.Quiz"
	import="java.util.ArrayList" import="MRC.MessageManager"
	import="Model.Message"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">

<title>Quiz Website <%
	AccountManager am = (AccountManager) request.getAttribute("accManager");

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
					<li class="sub-menu"><a href="./HomePageServlet"> <i
							class="fa fa-book"></i> <span>Home Page</span>
					</a></li>
					<li class="sub-menu"><a href="./createQuizServlet"> <i
							class=" fa"></i> <span>Create Quiz</span>
					</a></li>
					<li class="sub-menu"><a href="javascript:;"> <i class="fa"></i>
							<span>My Friends</span>
					</a></li>
					<li class="sub-menu"><a href="./friendsSearchServlet"> <i
							class="fa"></i> <span>Search Friends</span>
					</a></li>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Search Quizes</span>
					</a></li>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Change Password</span>
					</a></li>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Change Profile Picture</span>
					</a></li>
					<%
						if (isAdmin.equals("true")) {
					%>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Write Post</span>
					</a></li>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Make User Admin By Id</span>
					</a></li>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Bann User By Id</span>
					</a></li>
					<li class="sub-menu"><a href="javascript:;"> <i
							class=" fa"></i> <span>Remove Quiz</span>
					</a></li>
					<%
						}
					%>
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
							<%
								MessageManager mm = (MessageManager) request.getAttribute("MessManager");
								int len = 20;
								Message m = null;

								for (int i = 0; i < mm.getMessages().size(); i++) {
									m = mm.getMessages().get(i);
									String sender = m.getSenderName();
									String msg = m.getMessage();
									if (msg.length() > len) {
										msg = msg.substring(0, len) + "...";
									}
							%>
							<p>
								<%
									sender += "\t" + msg;
								%>
								<button onclick="readForm()">Read</button>
							</p>

							<br> <br>
							<%
								}
							%>
							<h5 class="centered">End Of Messages</h5>

							<%
								if (m != null) {
							%>
							<form name="readForm" action="./MRC/Message.jsp" method="GET">
								<input type="hidden" id="message" name="message"
									value="<%=m.getMessage()%>"> <input type="hidden"
									id="sender" name="sender" value="<%=m.getSenderName()%>">
							</form>
							<%
								}
							%>


						</div>
						<!-- /row -->

					</div>
					<!-- /col-lg-9 END SECTION MIDDLE -->


					<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->
					<div class="col-lg-3 ds">
						<!-- Admin Posts Start -->
						<h3>
							ADMIN POSTS <a href=""> -- see all</a>
						</h3>

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
						<!-- Admin Posts End -->

						<!-- Challenges Start -->
						<h3>
							NEW CHALLENGES <a href=""> -- see all</a>
						</h3>

						<%
							for (int i = 0; i < 4; i++) {
						%>
						<div class="desc">
							<div class="thumb">
								<img class="img-circle" src="./loggedIn/assets/img/ui-divya.jpg"
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
						<!-- Challenges End -->
					</div>
					<!-- RIGHT SLIDEBAR CONTENT END -->

				</div>
				<!--MAIN + RIGHT END -->
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
</body>
</html>