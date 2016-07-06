<%@ page import="java.sql.SQLException" import="dataSrc.DataSource"
	import="java.sql.Connection" import="java.util.ArrayList"
	import="friends.friendsDatabaseConnector"
	import="Model.Quiz" import="java.util.ArrayList"
	import="MRC.MessageManager" import="Model.Message"
	import="searchQuiz.searchQuizServlet" import="Model.Quiz"%>

<%@ include file="../MenuFiles/HeadOfFile.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<%@ include file="../MenuFiles/SidebarMenu.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->


<form align="center" action="./searchQuizServlet" method="post">
	Search Quizzes: <input type="text" name="quizName"
		placeholder="Quiz name"> <br> Select category: <select
		name="quizCategory">
		<option value="0">Physics</option>
		<option value="1">Mathematics</option>
		<option value="2">Aptitude</option>
		<option value="3">English</option>
		<option value="4">History</option>
		<option value="5">Star Wars</option>
		<option value="6">Biology</option>
	</select><br> <input type="submit" value="search" />
</form>

<h3 align="center">Such quiz does not exist!</h3>
<h3 align="center">Please try again..</h3>


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>
