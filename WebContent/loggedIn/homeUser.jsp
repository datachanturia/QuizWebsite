<%@page import="Model.Quiz" import="java.util.ArrayList"
	import="MRC.MessageManager" import="Model.Message"
	import="Database.RequestDaoImpl" import="java.sql.Connection"
	import="dataSrc.DataSource" import="java.sql.SQLException"%>

<%@ include file="../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../MenuFiles/SidebarMenu.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
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
			%>
			<form name="PQqzidd<%=i%>" method="get" action="./servletChallenge">
				<input type="hidden" name="quizID"
					value="<%=qzPQls.get(i).getQuizID()%>">
			</form>
			<form name="PQqzid<%=i%>" method="Post" action="./QuizSummaryServlet">
				<h5 align="left" style="border-left: 25px solid #444c57;">
					<a href="#" onclick="document.forms['PQqzid<%=i%>'].submit()">
						Take </a> or <a href='#'
						onclick="document.forms['PQqzidd<%=i%>'].submit()"> Challenge
					</a>
					<%=qzPQls.get(i).getQuizname()%>
					<input type="hidden" name="quizID"
						value="<%=qzPQls.get(i).getQuizID()%>">
				</h5>
			</form>
			<%
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
			%>
			<form name="NQqzidd<%=i%>" method="get" action="./servletChallenge">
				<input type="hidden" name="quizID"
					value="<%=qzNQls.get(i).getQuizID()%>">
			</form>
			<form name="NQqzid<%=i%>" method="Post" action="./QuizSummaryServlet">
				<h5 align="left" style="border-left: 25px solid #444c57;">
					<a href="#" onclick="document.forms['NQqzid<%=i%>'].submit()">
						Take </a> or <a href='#'
						onclick="document.forms['NQqzidd<%=i%>'].submit()"> Challenge
					</a>
					<%=qzNQls.get(i).getQuizname()%>
					<input type="hidden" name="quizID"
						value="<%=qzNQls.get(i).getQuizID()%>">
				</h5>
			</form>
			<%
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
			%>

			<form name="DPqzidd<%=i%>" method="get" action="./servletChallenge">
				<input type="hidden" name="quizID"
					value="<%=qzDPls.get(i).getQuizID()%>">
			</form>
			<form name="DPqzid<%=i%>" method="Post" action="./QuizSummaryServlet">
				<h5 align="left" style="border-left: 25px solid #444c57;">
					<a href="#" onclick="document.forms['DPqzid<%=i%>'].submit()">
						Take </a> or <a href='#'
						onclick="document.forms['DPqzidd<%=i%>'].submit()"> Challenge
					</a>
					<%=qzDPls.get(i).getQuizname()%>
					<input type="hidden" name="quizID"
						value="<%=qzDPls.get(i).getQuizID()%>">
				</h5>
			</form>
			<%
				}
			%>
		</div>
	</div>
	<!-- end 3 black panel -->
</div>
<!-- end row -->


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>