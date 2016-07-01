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
			<h5 class="centered"><%=hidden%></h5>
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
			<li class="sub-menu"><a href="javascript:;"> <i class=" fa"></i>
					<span>Search Quizes</span>
			</a></li>
			<%
				if (!am.getUser().isSoc()) {
			%>
			<li class="sub-menu"><a href="./ChangePassServlet"> <i
					class=" fa"></i> <span><button
							style="background-color: #555555">Change Password</button></span>
			</a></li>
			<li class="sub-menu"><a href="javascript:;"> <i class=" fa"></i>
					<span><button style="background-color: #555555">Change
							Profile Picture</button></span>
			</a></li>
			<%
				}
			%>
			<%
				if (isAdmin.equals("true")) {
			%>
			<li class="sub-menu"><a href="javascript:;"> <i class=" fa"></i>
					<span>Write Post</span>
			</a></li>
			<li class="sub-menu"><a href="javascript:;"> <i class=" fa"></i>
					<span>Make User Admin By Id</span>
			</a></li>
			<li class="sub-menu"><a href="javascript:;"> <i class=" fa"></i>
					<span>Bann User By Id</span>
			</a></li>
			<li class="sub-menu"><a href="javascript:;"> <i class=" fa"></i>
					<span>Remove Quiz</span>
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