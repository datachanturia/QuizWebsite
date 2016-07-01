<%@ page import="ULS.AccountManager" import="Model.Quiz"
	import="java.util.ArrayList" import="MRC.MessageManager"
	import="Model.Message"%>

<%@ include file="../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../MenuFiles/SidebarMenu.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
<!--main content start-->
<section id="main-content">
	<section class="wrapper">

		<div class="row">
			<div class="col-lg-9 main-chart">

				<!-- start row -->
				<div class="row mt">
					<!-- start 1 black panel -->
					<div class="col-md-4 col-sm-4 mb">
						<div class="darkblue-panel pn">
							<div class="darkblue-header">
								<h5>TOP POPULAR QUIZES</h5>
							</div>
							<%
								ArrayList<Quiz> qzPQls = (ArrayList<Quiz>) request.getAttribute("popQuizLs");

								for (int i = 0; i < qzPQls.size(); i++) {
									out.println("<h5>" + qzPQls.get(i).getQuizname() + " Quiz"
											+ " <button style=\"background-color:#555555\"> Take Quiz </button></h5>");
								}
							%>
						</div>
					</div>
					<!-- end 1 black panel -->

					<!-- start 2 black panel -->
					<div class="col-md-4 col-sm-4 mb">
						<div class="darkblue-panel pn">
							<div class="darkblue-header">
								<h5>TOP NEW QUIZES</h5>
							</div>
							<%
								ArrayList<Quiz> qzNQls = (ArrayList<Quiz>) request.getAttribute("newQuizLs");

								for (int i = 0; i < qzNQls.size(); i++) {
									out.println("<h5> <" + qzNQls.get(i).getCreationDate() + "> " + qzNQls.get(i).getQuizname() + " Quiz"
											+ " <button style=\"background-color:#555555\"> Take Quiz </button></h5>");
								}
							%>
						</div>
					</div>
					<!-- end 2 black panel -->

					<!-- start 3 black panel -->
					<div class="col-md-4 mb">
						<!-- BLACK PANNEL - TOP FRIENDS -->
						<div class="darkblue-panel pn">
							<div class="darkblue-header">
								<h5>QUIZES OF THE DAY</h5>
							</div>
							<%
								ArrayList<Quiz> qzDPls = (ArrayList<Quiz>) request.getAttribute("dayPopuLs");

								for (int i = 0; i < qzDPls.size(); i++) {
									out.println("<h5>" + qzDPls.get(i).getQuizname() + " Quiz"
											+ " <button style=\"background-color:#555555\"> Take Quiz </button></h5>");
								}
							%>
						</div>
					</div>
					<!-- end 3 black panel -->
				</div>
				<!-- end row -->

			</div>
			<!-- END SECTION MIDDLE -->


			<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

			<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>