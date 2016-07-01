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
<form align="center" action="./CrQuizServlet" method="post">
	Quiz Name: <input type="text" name="quizName"
		placeholder="Enter Quiz Name Here"><br> Quiz Category: <select
		name="QuizCategory">
		<option value="0">Question Response</option>
		<option value="1">Fill In The Blank</option>
		<option value="2">Multiple Choice</option>
		<option value="3">Picture Response</option>
		<option value="4">Multiple Answer</option>
		<option value="5">Multiple Choice Multiple Answer</option>
	</select> <br> <br> Description:
	<textarea name="description" rows="2" cols="30"
		placeholder="Enter Text Here"></textarea>
	<br> <br> Score: <input type="text" name="score"
		placeholder="Enter Score Here"><br> <br> <input
		type="checkbox" name="random" value="isRandom">Random
	Questions<br> <br> Display Questions On: <input type="radio"
		name="Page" value="Multiple">Multiple pages <input
		type="radio" name="Page" value="One">One Page <br> <br>
	<input type="checkbox" name="immediate" value="immediate">Immediate
	Correction<br> <br> <input type="checkbox" name="practice"
		value="practice">Practice Mode<br> <br>

	<div id="Questions">

		Question Type: <select name="QuestionType_1">
			<option value="0">Question Response</option>
			<option value="1">Fill In The Blank</option>
			<option value="2">Multiple Choice</option>
			<option value="3">Picture Response</option>
			<option value="4">Multiple Answer</option>
			<option value="5">Multiple Choice Multiple Answer</option>
		</select> <br> <br> Question Text:
		<textarea name="Question_1" rows="2" cols="30"
			placeholder="Enter Question Here"></textarea>
		<br> Answer:
		<textarea name="Question_1Answer" rows="2" cols="30"
			placeholder="Enter Answer Here"></textarea>
		<input type="checkbox" name="Question_1AnswerCorrect" value="q1a1">Correct
		Answer

	</div>
	<input type="button" id="add_answer()" onClick="addAnswer()"
		value="Add Answer" /> <input type="button" id="add_kid()"
		onClick="addQuestion()" value="Add Question" /> <input type="submit"
		name="submit" value="submit" />
</form>


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>