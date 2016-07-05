package View;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
import ULS.AccountManager;
import dataSrc.DataSource;
import jdk.internal.org.objectweb.asm.commons.GeneratorAdapter;

/**
 * Servlet implementation class CheckAnswersServlet
 */
@WebServlet("/CheckAnswersServlet")
public class CheckAnswersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAnswersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		HttpSession ses = request.getSession();
		ArrayList<Question> questions = (ArrayList<Question>)ses.getAttribute("questions");
		Quiz quiz = (Quiz)ses.getAttribute("quiz");
		int quizid = quiz.getQuizID();
		int quizScore = quiz.getScore();
		AnswerDaoImpl ansdao = null;
		QuizDaoImpl quizdao = null;
		try {
			con = DataSource.getInstance().getConnection();
			quizdao = new QuizDaoImpl(con);
			ansdao = new AnswerDaoImpl(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int maxQuestionScore = quizScore/questions.size();
		int score = 0;
		String answer = "";
		ArrayList<Answer> answers = null;
		for(Question q : questions){
			switch (q.getType()) {
			case Question_Response:
				answer = request.getParameter(Integer.toString(q.getQuestionID()));
				answers = ansdao.getQuestionAnswers(q.getQuestionID());
				for(Answer a:answers){
					if(a.getAnswer().equals(answer)){
						score+=maxQuestionScore;
						break;
					}
				}
				break;
			case Fill_In_The_Blank:
				answer = request.getParameter(Integer.toString(q.getQuestionID()));
				answers = ansdao.getQuestionAnswers(q.getQuestionID());
				for(Answer a:answers){
					if(a.getAnswer().equals(answer)){
						score+=maxQuestionScore;
						break;
					}
				}
				break;
			case Multiple_Choice:
				answer = request.getParameter(Integer.toString(q.getQuestionID()));
				if(answer!=null && ansdao.isCorrect(Integer.parseInt(answer))){
					score+=maxQuestionScore;
				}
				break;
			case Picture_Response:
				answer = request.getParameter(Integer.toString(q.getQuestionID()));
				answers = ansdao.getQuestionAnswers(q.getQuestionID());
				for(Answer a:answers){
					if(a.getAnswer().equals(answer)){
						score += maxQuestionScore;
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
						currentscore += (double)maxQuestionScore/correctcounter;
					}else{
						currentscore -= (double)maxQuestionScore/correctcounter;
					}
				}
				if(currentscore>0)
					score+=currentscore;
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
				score+= maxQuestionScore*(currentscore/answers.size());
				break;

			default:
				break;
			}
		}
		
		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");
		long d = (Long)ses.getAttribute("startTime");
		long diffMins = ((new java.util.Date()).getTime()-d)/(60*1000)%60;
		
		quizdao.addUserTakenQuiz(am.getUser().getUserID(), quizid, new Date((new java.util.Date()).getTime()), score,diffMins);
		
		
		if (con != null)
	 		try {
	 			con.close();
	 		} catch (SQLException e) {
	 		}
		request.setAttribute("score", score);
		request.setAttribute("quizID", quizid);
		request.setAttribute("time", diffMins);
		RequestDispatcher rd = request.getRequestDispatcher("./Quiz/ResultPage.jsp");
		rd.forward(request, response);
	}

}
