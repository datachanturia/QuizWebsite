package View;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.AnswerDaoImpl;
import Database.QuestionDaoImpl;
import Database.QuizDaoImpl;
import Model.Answer;
import Model.Question;
import Model.Quiz;
import dataSrc.DataSource;

/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String QuestionHtml(int questionIndx,Question q,AnswerDaoImpl ad){
    	ArrayList<Answer> answers = null;
    	String html = "<div class=\"question\">";
    	html += ("Question #"+questionIndx+":<br>");
    	switch(q.getType()){
	    	case Question_Response:
	    		html += (q.getQuestion() + "<br><br>");
	    		html += ("<textarea name="+q.getQuestionID()+"rows=\"2\" cols=\"30\" placeholder=\"Enter Your Answer Here\"></textarea><br><br>");
	    		break;
	    	case Fill_In_The_Blank:
	    		String[] tokens = q.getQuestion().split("#\\$%\\$#");
	    		html += (tokens[0] + "<input type=\"text\" name=\""+q.getQuestionID()+"\" placeholder=\"Enter Your Answer Here\"></textarea>");
	    		html += (tokens[1] + "<br><br>");
	    		break;
	    	case Multiple_Choice:
	    		html += (q.getQuestion() + "<br><br>");
	    		answers = ad.getQuestionAnswers(q.getQuestionID());
	    		for(Answer a : answers){
	    			html += ("<input type=\"radio\" name=\""+q.getQuestionID()+"\" value=\""+a.getAnswerID()+"\">"+a.getAnswer()+"<br><br>");
	    		}
	    		break;
	    	case Picture_Response:
	    		html += ("<img src=\""+q.getQuestion()+"\" alt=\"some_text\"><br><br>");
	    		html += ("<input type=\"text\" name=\""+q.getQuestionID()+"\" placeholder=\"Enter Your Answer Here\"></textarea><br><br>");
	    		break;
	    	case Mult_Choice_Answer:
	    		html += (q.getQuestion() + "<br><br>");
	    		answers = ad.getQuestionAnswers(q.getQuestionID());
	    		for(Answer a : answers){
	    			html += ("<input type=\"checkbox\" name=\""+q.getQuestionID()+"\" value=\""+a.getAnswerID()+"\">"+a.getAnswer()+"<br><br>");
	    		}
	    		break;
	    	case Multi_Answer:
	    		html += (q.getQuestion() + "<br><br>");
	    		answers = ad.getQuestionAnswers(q.getQuestionID());
	    		for(Answer a : answers){
	    			html += ("<input type=\"text\" name=\""+q.getQuestionID()+"\"><br><br>");
	    		}
	    		break;
	    	default:break;
    	}
    	html+= "</div>";
    	return html;
    }
    
    private boolean checkAnswer(Question q, HttpServletRequest request,AnswerDaoImpl ansdao){
    	boolean iscorrect = false;
    	String answer = "";
		ArrayList<Answer> answers = null;
    	switch (q.getType()) {
		case Question_Response:
			answer = request.getParameter(Integer.toString(q.getQuestionID()));
			answers = ansdao.getQuestionAnswers(q.getQuestionID());
			for(Answer a:answers){
				if(a.getAnswer().equals(answer)){
					iscorrect = true;
					break;
				}
			}
			break;
		case Fill_In_The_Blank:
			answer = request.getParameter(Integer.toString(q.getQuestionID()));
			answers = ansdao.getQuestionAnswers(q.getQuestionID());
			for(Answer a:answers){
				if(a.getAnswer().equals(answer)){
					iscorrect = true;
					break;
				}
			}
			break;
		case Multiple_Choice:
			answer = request.getParameter(Integer.toString(q.getQuestionID()));
			if(answer!=null && ansdao.isCorrect(Integer.parseInt(answer))){
				iscorrect = true;
			}
			break;
		case Picture_Response:
			answer = request.getParameter(Integer.toString(q.getQuestionID()));
			answers = ansdao.getQuestionAnswers(q.getQuestionID());
			for(Answer a:answers){
				if(a.getAnswer().equals(answer)){
					iscorrect = true;
					break;
				}
			}
			break;
		case Mult_Choice_Answer:
			String[] answerIDs1 = request.getParameterValues(Integer.toString(q.getQuestionID()));
			if(answerIDs1 == null)break;
			answers = ansdao.getQuestionAnswers(q.getQuestionID());
			int correctcounter = 0;
			for(Answer a : answers){
				if(a.getCorrect())
					correctcounter++;
			}
			double currentscore = 0;
			for (int i = 0; i < answerIDs1.length; i++) {
				if(ansdao.isCorrect(Integer.parseInt(answerIDs1[i]))){
					currentscore += (double)5/correctcounter;
				}else{
					currentscore -= (double)5/correctcounter;
				}
			}
			if(currentscore>0)
				iscorrect = true;
			break;
		case Multi_Answer:
			String[] answerstrs = request.getParameterValues(Integer.toString(q.getQuestionID()));
			if(answerstrs == null)
				break;
			answers = ansdao.getQuestionAnswers(q.getQuestionID());
			currentscore = 0;
			for (int i = 0; i < answerstrs.length; i++) {
				for(Answer a : answers){
					if(a.getAnswer().equals(answerstrs[i])){
						currentscore++;
					}
				}
			}
			break;

		default:
			break;
		}
    	return iscorrect;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("QUESTIONSERVLET:GET");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession();
		int index = (Integer)ses.getAttribute("currindex");
		Connection con = null;
		ArrayList<Question> questions = (ArrayList<Question>)ses.getAttribute("questions");
		if(index >= questions.size()){
			request.getRequestDispatcher("CheckAnswersServlet").forward(request, response);
			index--;
		}
		
		Quiz quiz = (Quiz)ses.getAttribute("quiz");
		try {
			con = DataSource.getInstance().getConnection();
			AnswerDaoImpl ansdao = new AnswerDaoImpl(con);
			String s = QuestionHtml(index+1, questions.get(index), ansdao);
			if(quiz.isImmediate_correction()){
				if(checkAnswer(questions.get(index-1), request, ansdao))
					s+="CORRECT";
				else{
					s+="INCORRECT";
				}
			}
			response.getWriter().println(s);
			ses.setAttribute("currindex", index+1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
		 	if (con != null)
		 		try {
		 			con.close();
		 		} catch (SQLException e) {
		 		}
		 }
	}

}
