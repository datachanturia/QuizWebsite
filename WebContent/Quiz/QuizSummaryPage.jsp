<%@page import="Database.UserDaoImpl"%>
<%@page import="Model.User"%>
<%@page import="Model.TakenQuiz"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="Model.Quiz"%>
<%@page import="Database.QuizDaoImpl"%>
<%@page import="java.sql.SQLException"%>
<%@page import="dataSrc.DataSource"%>

<%@ include file="../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../MenuFiles/SidebarMenu.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
<%
	int userid = am.getUser().getUserID();
	int quizid = Integer.parseInt(request.getParameter("quizID"));
	boolean isChallenge = request.getParameter("challengerID") != null;
	
	int challengerID = 0;
	if(isChallenge){
		challengerID = Integer.parseInt(request.getParameter("challengerID"));
	}
	Connection con = null;
	Quiz currquiz = null;
	ArrayList<TakenQuiz> taken = null;
	String authorName = null;
	ArrayList<TakenQuiz> topperformers = null;
	ArrayList<TakenQuiz> topperformerslastday = null;
	ArrayList<TakenQuiz> recentperformers = null;
	TakenQuiz challenger = null;
	UserDaoImpl userdao = null;
	try {
		con = DataSource.getInstance().getConnection();
		QuizDaoImpl quizdao = new QuizDaoImpl(con);
		currquiz = quizdao.getQuiz(quizid);
		taken = quizdao.takenquiz(currquiz.getQuizID(), userid);
		topperformers = quizdao.topPerformers(quizid, false, false);
		topperformerslastday = quizdao.topPerformers(quizid, true, false);
		recentperformers = quizdao.topPerformers(quizid, false, true);
		userdao = new UserDaoImpl(con);
		if(isChallenge){
			challenger = quizdao.userMaxInQuiz(quizid, challengerID);
			if(challenger == null){
				out.println("<label>"+userdao.getUserName(challengerID)+" Has Not Done This Quiz Yet </label><br><br>");
			}else{
				out.println("<label>"+userdao.getUserName(challengerID)+"'s Max Score : </label>"+challenger.getScore());
			}
		}
		authorName = userdao.getUserName(currquiz.getAuthorID());
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<label>Quiz Name : </label>
<%=currquiz.getQuizname()%><br>
<br>
<label>Description : </label>
<%=currquiz.getDescription()%><br>
<br>
<label>Author : </label>
<%
	out.print("<a href = \"ServletToCab?friendID="+currquiz.getAuthorID()+"\"> ");
	out.print(authorName);
	out.print("</a>");
%>
<script type="text/javascript" src="Quiz/sorttable.js"></script> 

<table class="sortable" border="1" style="width: 50%" >
	<caption>Your Last Attempts</caption>
	<thead>
	<tr>
		<th>Date</th>
		<th>Time</th>
		<th>Score</th>
		<th>Percent</th>
	</tr>
	</thead>
	<tbody>
	<%
		int quizScore = currquiz.getScore();
		for (TakenQuiz t : taken) {
			out.println("<tr>");
			out.print("<td>");
			out.print(t.getTakeDate().toString());
			out.print("</td>\n");
			out.print("<td>");
			out.print(t.getQuiztime());
			out.print("</td>\n");
			out.print("<td>");
			out.print(t.getScore());
			out.print("</td>\n");
			out.print("<td>");
			out.print(Math.floor(((double) t.getScore() / quizScore) * 100) + "%");
			out.print("</td>\n");
			out.println("</tr>");
		}
	%>
	</tbody>
</table>
<br>

<table border="1" style="width: 50%">
	<caption>Top Performers</caption>
	<tr>
		<th>User</th>
		<th>Date</th>
		<th>Time</th>
		<th>Score</th>
		<th>Percent</th>
	</tr>
	<%
		for (TakenQuiz t : topperformers) {
			out.println("<tr>");
			out.print("<td>");
			out.print(userdao.getUserName(t.getUserID()));
			out.print("</td>\n");
			out.print("<td>");
			out.print(t.getTakeDate().toString());
			out.print("</td>\n");
			out.print("<td>");
			out.print(t.getQuiztime());
			out.print("</td>\n");
			out.print("<td>");
			out.print(t.getScore());
			out.print("</td>\n");
			out.print("<td>");
			out.print(Math.floor(((double) t.getScore() / quizScore) * 100) + "%");
			out.print("</td>\n");
			out.println("</tr>");
		}
	%>
</table>
<br>

<table border="1" style="width: 50%">
	<caption>Top Performers Last Day</caption>
	<tr>
		<th>User</th>
		<th>Time</th>
		<th>Score</th>
		<th>Percent</th>
	</tr>
	<%
		for (TakenQuiz t : topperformerslastday) {
			out.println("<tr>");
			out.print("<td>");
			out.print(userdao.getUserName(t.getUserID()));
			out.print("</td>\n");
			out.print("<td>");
			out.print(t.getQuiztime());
			out.print("</td>\n");
			out.print("<td>");
			out.print(t.getScore());
			out.print("</td>\n");
			out.print("<td>");
			out.print(Math.floor(((double) t.getScore() / quizScore) * 100) + "%");
			out.print("</td>\n");
			out.println("</tr>");
		}
	%>
</table>
<br>

<table border="1" style="width: 50%">
	<caption>Recent Attempts</caption>
	<tr>
		<th>User</th>
		<th>Score</th>
		<th>Percent</th>
	</tr>
	<%
		for (TakenQuiz t : recentperformers) {
			out.println("<tr>");
			out.print("<td>");
			out.print(userdao.getUserName(t.getUserID()));
			out.print("</td>\n");
			out.print("<td>");
			out.print(t.getScore());
			out.print("</td>\n");
			out.print("<td>");
			out.print(Math.floor(((double) t.getScore() / quizScore) * 100) + "%");
			out.print("</td>\n");
			out.println("</tr>");
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	%>
</table>
<br>

<form action="AttemptQuizServlet" method="post">
	<input type="hidden" name="quizID" value=<%=quizid%>> <input
		type="hidden" name="isMultiple"
		value="<%=currquiz.isMultiple_page()%>"> <input type="hidden"
		name="isRandom" value="<%=currquiz.isRandom()%>"> <input
		type="hidden" name="isImmediate"
		value="<%=currquiz.isImmediate_correction()%>"> <input
		type="submit" value="Attempt Quiz">
</form>


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>

