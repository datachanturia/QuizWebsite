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
		<option value="0">Question Response</option>
		<option value="1">Fill In The Blank</option>
		<option value="2">Multiple Choice</option>
		<option value="3">Picture Response</option>
		<option value="4">Multiple Answer</option>
		<option value="5">Multiple Choice Multiple Answer</option>
	</select><br> <input type="submit" value="search" />
</form>

<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>
