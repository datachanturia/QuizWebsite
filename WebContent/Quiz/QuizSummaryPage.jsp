<%@page import="Database.UserDaoImpl"%>
<%@page import="Model.User"%>
<%@page import="Model.TakenQuiz"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="Model.Quiz"%>
<%@page import="Database.QuizDaoImpl"%>
<%@page import="java.sql.SQLException"%>
<%@page import="dataSrc.DataSource"%>
<%@page import="ULS.AccountManager" %>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Summary</title>
</head>
<body>
<%
AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");
int userid = am.getUser().getUserID();
int quizid = Integer.parseInt(request.getParameter("quizID"));
Connection con = null;
Quiz currquiz = null;
ArrayList<TakenQuiz> taken = null;
String authorName = null;
ArrayList<TakenQuiz> topperformers = null;
ArrayList<TakenQuiz> topperformerslastday = null;
ArrayList<TakenQuiz> recentperformers =null;
UserDaoImpl userdao = null;
try {
	con = DataSource.getInstance().getConnection();
	QuizDaoImpl quizdao = new QuizDaoImpl(con);
	currquiz = quizdao.getQuiz(quizid);
	taken = quizdao.takenquiz(currquiz.getQuizID(),userid);
	topperformers = quizdao.topPerformers(quizid,false,false);
	topperformerslastday = quizdao.topPerformers(quizid,true,false);
	recentperformers = quizdao.topPerformers(quizid,false,true);
	userdao = new UserDaoImpl(con);
	authorName = userdao.getUserName(currquiz.getAuthorID());
} catch (Exception e) {
	e.printStackTrace();
}
%>
<label>Quiz Name : </label> <%=currquiz.getQuizname() %><br><br>
<label>Description : </label> <%=currquiz.getDescription() %><br><br>
<label>Author : </label> 
<%
out.print("<a href = \"servlet.html\"> ");
out.print(authorName) ;
out.print("</a>");
%>

<table border="1" style="width:50%">
<caption>Your Last Attempts</caption>
  <tr>
    <th>Date</th>
    <th>Time</th>
    <th>Score</th>
    <th>Percent</th>
  </tr>
<%
int quizScore = currquiz.getScore();
for(TakenQuiz t : taken){
		out.println("<tr>");
		out.print("<td>");
		out.print(t.getTakeDate().toString());
		out.print("</td>\n");
		out.print("<td>");
		out.print(t.getTakeDate().toString());
		out.print("</td>\n");
		out.print("<td>");
		out.print(t.getScore());
		out.print("</td>\n");
		out.print("<td>");
		out.print(Math.floor(((double)t.getScore()/quizScore)*100)+"%");
		out.print("</td>\n");
		out.println("</tr>");
}
%>
</table><br>

<table border="1" style="width:50%">
<caption>Top Performers</caption>
  <tr>
    <th>User</th>
    <th>Date</th>
    <th>Score</th>
    <th>Percent</th>
  </tr>
<%

for(TakenQuiz t : topperformers){
		out.println("<tr>");
		out.print("<td>");
		out.print(userdao.getUserName(t.getUserID()));
		out.print("</td>\n");
		out.print("<td>");
		out.print(t.getTakeDate().toString());
		out.print("</td>\n");
		out.print("<td>");
		out.print(t.getScore());
		out.print("</td>\n");
		out.print("<td>");
		out.print(Math.floor(((double)t.getScore()/quizScore)*100)+"%");
		out.print("</td>\n");
		out.println("</tr>");
}
%>
</table><br>

<table border="1" style="width:50%">
<caption>Top Performers Last Day</caption>
  <tr>
    <th>User</th>
    <th>Score</th>
    <th>Percent</th>
  </tr>
<%

for(TakenQuiz t : topperformerslastday){
		out.println("<tr>");
		out.print("<td>");
		out.print(userdao.getUserName(t.getUserID()));
		out.print("</td>\n");
		out.print("<td>");
		out.print(t.getScore());
		out.print("</td>\n");
		out.print("<td>");
		out.print(Math.floor(((double)t.getScore()/quizScore)*100)+"%");
		out.print("</td>\n");
		out.println("</tr>");
}
%>
</table><br>

<table border="1" style="width:50%">
<caption>Recent Attempts</caption>
  <tr>
    <th>User</th>
    <th>Score</th>
    <th>Percent</th>
  </tr>
<%

for(TakenQuiz t : recentperformers){
		out.println("<tr>");
		out.print("<td>");
		out.print(userdao.getUserName(t.getUserID()));
		out.print("</td>\n");
		out.print("<td>");
		out.print(t.getScore());
		out.print("</td>\n");
		out.print("<td>");
		out.print(Math.floor(((double)t.getScore()/quizScore)*100)+"%");
		out.print("</td>\n");
		out.println("</tr>");
}

if (con != null){
	try {
		con.close();
	} catch (SQLException e) {
	}
}
%>
</table><br>

<form action="attemptQuizServlet">
  <input type="hidden" name="quizID" value=<%=quizid%>>
  <input type="submit" value="Attempt Quiz">
</form>






</body>
</html>