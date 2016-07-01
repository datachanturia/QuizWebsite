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
Connection con = null;
Quiz currquiz = null;
ArrayList<TakenQuiz> taken = null;
String authorName = null;
try {
	con = DataSource.getInstance().getConnection();
	QuizDaoImpl quizdao = new QuizDaoImpl(con);
	currquiz = quizdao.getQuiz(1);
	taken = quizdao.takenquiz(userid, currquiz.getQuizID());
	UserDaoImpl userdao = new UserDaoImpl(con);
	authorName = userdao.getUserName(currquiz.getAuthorID());
} catch (Exception e) {
	e.printStackTrace();
} finally {
	if (con != null)
		try {
			con.close();
		} catch (SQLException e) {
		}
}
%>
<label>Quiz Name : </label> <%=currquiz.getQuizname() %><br><br>
<label>Description : </label> <%=currquiz.getDescription() %><br><br>
<label>Author : </label> 
<%
out.print("< a href = "+"servleti romelic userpages agenerirebs"+"> ");
out.print(authorName) ;
out.print("</a>");
%>

<table style="width:50%">
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
		out.print((t.getScore()/quizScore)*100+"%");
		out.print("</td>\n");
		out.println("</tr>");
}
%>
</table>



</body>
</html>