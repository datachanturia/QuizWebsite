<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Database.UserDaoImpl"%>
<%@page import="Model.User"%>
<%@page import="Model.TakenQuiz"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="Model.Quiz"%>
<%@page import="Database.QuizDaoImpl"%>
<%@page import="java.sql.SQLException"%>
<%@page import="dataSrc.DataSource"%>


<%
Connection con = null;
HttpSession ses = request.getSession();
Quiz currquiz = (Quiz)ses.getAttribute("quiz");
int quizScore = currquiz.getScore();
ArrayList<TakenQuiz> topperformers = null;
UserDaoImpl userdao = null;
try {
	con = DataSource.getInstance().getConnection();
	QuizDaoImpl quizdao = new QuizDaoImpl(con);
	topperformers = quizdao.topPerformers(currquiz.getQuizID(),false,false);
	userdao = new UserDaoImpl(con);
} catch (Exception e) {
	e.printStackTrace();
}

%>
Your Score: <%=request.getAttribute("score") %> <br><br>
Time: <%=request.getAttribute("time") %><br><br>

<table border="1" style="width:50%">
<caption>Top Performers</caption>
  <tr>
    <th>User</th>
    <th>Date</th>
    <th>Time</th>
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
		out.print(t.getQuiztime());
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



