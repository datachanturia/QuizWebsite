<%@ page import="Model.Quiz"
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
<script type="text/javascript">
	var i = 1;
	var j = 1;
	var additionalfieldcounter = 0
	function addQuestion() {
		i++;
		j = 1;
		additionalfieldcounter=0;
		var div = document.createElement('div');
		div.setAttribute('class', 'myclass');
		div.innerHTML = '<br>'
				+ '---------------------------------------------------------------------------------------------------------------------------------------'
				+ '<br>'
				+ '<div id="Questiondiv_'+i+'">Question Type: <select name="QuestionType_'+i+'" onchange=\'addField(this)\'><option value="0">Question Response</option><option value="1">Fill In The Blank</option><option value="2">Multiple Choice</option><option value="3">Picture Response</option><option value="4">Multiple Answer</option><option value="5">Multiple Choice Multiple Answer</option></select><br><br>Question Text: <textarea name="Question_'+i+'" rows="2" cols="30" placeholder="Enter Question Here" required></textarea></div><br><input type="button" id="removeQuestion()" onClick="removeQuestion(this)" value="Remove Question" /><br>Answer: <textarea name="Question_'+i+'Answer" rows="2" cols="30" placeholder="Enter Answer Here" required></textarea><input type="checkbox" name="Question_'+i+'AnswerCorrect" value="q'+i+'a'+j+'">Correct Answer<br>';
		document.getElementById('Questions').appendChild(div);

	}

	function removeQuestion(div) {
		document.getElementById('Questions').removeChild(div.parentNode);
		i--;
	}

	function addAnswer() {
		j++;
		var div = document.createElement('div');
		div.setAttribute('class', 'myclass');
		div.innerHTML = 'Answer: <textarea name="Question_'+i+'Answer" rows="2" cols="30" placeholder="Enter Answer Here" required></textarea><input type="checkbox" name="Question_'+i+'AnswerCorrect" value="q'+i+'a'+j+'">Correct Answer<input type="button" id="remove_answer()" onClick="removeAnswer(this)" value="Remove Answer" /><br>';
		document.getElementById('Questions').appendChild(div);

	}

	function removeAnswer(div) {
		document.getElementById('Questions').removeChild(div.parentNode);
	}
	
	function addField(div){
		 var element=document.getElementById(div.parentNode.id);
		 var currid = parseInt(div.name.split("_").pop());
		 if(div.value==1 && additionalfieldcounter==0){
			document.getElementsByName("Question_"+currid)[0].value="Enter Text Before Blank Here";
			var input = document.createElement("textarea");
			input.type = "text";
		    input.name = "member" + currid;
		    input.id = "member" + currid;
		    input.placeholder = "Enter Text After Blank Here";
		    element.appendChild(input);
		    additionalfieldcounter++;
		}else{
			element.removeChild(document.getElementById("member"+currid));
			document.getElementsByName("Question_"+currid)[0].value="Enter Question Here";
			if(additionalfieldcounter > 0)
				additionalfieldcounter--;
			
		}
	}
</script>

<form align="center" action="./CrQuizServlet" method="post">
	Quiz Name: <input type="text" name="quizName"
		placeholder="Enter Quiz Name Here" required><br> Quiz Category: <select
		name="QuizCategory">
		<option value="0">Physics</option>
		<option value="1">Mathematics</option>
		<option value="2">Aptitude</option>
		<option value="3">English</option>
		<option value="4">History</option>
		<option value="5">Star Wars</option>
		<option value="6">Biology</option>
	</select> <br> <br> Description:
	<textarea name="description" rows="2" cols="30"
		placeholder="Enter Text Here" required></textarea>
	<br> <br> Score: <input type="number" name="score"
		placeholder="Enter Score Here" required><br> <br> <input
		type="checkbox" name="random" value="isRandom">Random
	Questions<br> <br> Display Questions On: <input type="radio"
		name="Page" value="Multiple">Multiple pages <input
		type="radio" name="Page" value="One" checked="checked">One Page <br> <br>
	<input type="checkbox" name="immediate" value="immediate">Immediate
	Correction<br> <br> <input type="checkbox" name="practice"
		value="practice">Practice Mode<br> <br> <br>
	---------------------------------------------------------------------------------------------------------------------------------------
	<br>

	<div id="Questions">
		<div id="Questiondiv_1">
		Question Type: <select name="QuestionType_1" onchange='addField(this)'>
			<option value="0">Question Response</option>
			<option value="1">Fill In The Blank</option>
			<option value="2">Multiple Choice</option>
			<option value="3">Picture Response</option>
			<option value="4">Multiple Answer</option>
			<option value="5">Multiple Choice Multiple Answer</option>
		</select> <br> <br> Question Text:
		<textarea name="Question_1" rows="2" cols="30"
			placeholder="Enter Question Here" required></textarea>
		</div>
		<br> Answer:
		<textarea name="Question_1Answer" rows="2" cols="30"
			placeholder="Enter Answer Here" required></textarea>
		<input type="checkbox" name="Question_1AnswerCorrect" value="q1a1">Correct
		Answer
	</div>

	<br>
	---------------------------------------------------------------------------------------------------------------------------------------
	<br> <input type="button" id="add_answer()" onClick="addAnswer()"
		value="Add Answer" /> <input type="button" id="add_kid()"
		onClick="addQuestion()" value="Add Question" /> <input type="submit"
		name="submit" value="submit" />
</form>


<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT AND FOOTER
      *********************************************************************************************************************************************************** -->

<%@ include file="../MenuFiles/RightSidebarNFooter.jsp"%>