<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@page import="Database.QuestionDaoImpl"%>
<%@page import="Database.UserDaoImpl"%>
<%@page import="Model.User"%>
<%@page import="Model.TakenQuiz"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="Model.Quiz"%>
<%@page import="Database.QuizDaoImpl"%>
<%@page import="java.sql.SQLException"%>
<%@page import="dataSrc.DataSource"%>
<%@page import="Database.QuestionDaoImpl" %>
<%@page import="Model.Question" %>
<%@page import="Model.Answer" %>
<%@page import= "Database.AnswerDaoImpl" %>


<%@ include file="../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../MenuFiles/SidebarMenu.jsp"%>


<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

<%
Connection con = null;
HttpSession ses = request.getSession();
int quizid = Integer.parseInt(request.getParameter("quizID"));
ArrayList<Question> questions = (ArrayList<Question>)ses.getAttribute("questions");
Quiz quiz = (Quiz)ses.getAttribute("quiz");
try {
	con = DataSource.getInstance().getConnection();
} catch (Exception e) {
	e.printStackTrace();
}
%>

<form action="CheckAnswersServlet" method="post">
<%

AnswerDaoImpl ad = new AnswerDaoImpl(con);
for(int i = 0; i < questions.size();i++){
	Question q = questions.get(i);
	ArrayList<Answer> answers = null;
	out.println("Question #"+(i+1)+":<br>");
	switch(q.getType()){
	case Question_Response:
		out.println(q.getQuestion() + "<br><br>");
		out.println("<textarea name="+q.getQuestionID()+"rows=\"2\" cols=\"30\" placeholder=\"Enter Your Answer Here\"></textarea><br><br>");
		break;
	case Fill_In_The_Blank:
		String[] tokens = q.getQuestion().split("#\\$%\\$#");
		out.println(tokens[0] + "<input type=\"text\" name=\""+q.getQuestionID()+"\" placeholder=\"Enter Your Answer Here\"></textarea>");
		out.println(tokens[1] + "<br><br>");
		break;
	case Multiple_Choice:
		out.println(q.getQuestion() + "<br><br>");
		answers = ad.getQuestionAnswers(q.getQuestionID());
		for(Answer a : answers){
			out.println("<input type=\"radio\" name=\""+q.getQuestionID()+"\" value=\""+a.getAnswerID()+"\">"+a.getAnswer()+"<br><br>");
		}
		break;
	case Picture_Response:
		out.println("<img src=\""+q.getQuestion()+"\" alt=\"some_text\"><br><br>");
		out.println("<input type=\"text\" name=\""+q.getQuestionID()+"\" placeholder=\"Enter Your Answer Here\"></textarea><br><br>");
		break;
	case Mult_Choice_Answer:
		out.println(q.getQuestion() + "<br><br>");
		answers = ad.getQuestionAnswers(q.getQuestionID());
		for(Answer a : answers){
			out.println("<input type=\"checkbox\" name=\""+q.getQuestionID()+"\" value=\""+a.getAnswerID()+"\">"+a.getAnswer()+"<br><br>");
		}
		break;
	case Multi_Answer:
		out.println(q.getQuestion() + "<br><br>");
		answers = ad.getQuestionAnswers(q.getQuestionID());
		for(Answer a : answers){
			out.println("<input type=\"text\" name=\""+q.getQuestionID()+"\"><br><br>");
		}
		break;
	}
}
if (con != null)
	try {
		con.close();
	} catch (SQLException e) {
	}
%>
<input type="hidden" name="score" value="<%=quiz.getScore() %>">
<input type="hidden" name="quizID" value="<%=quizid %>">
<input type="submit" value="Submit">
</form>
