package View;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.QuizDao;
import Database.QuizDaoImpl;
import Model.Answer;
import Model.Question;
import Model.Quiz;
import Model.User;
import ULS.AccountManager;
import dataSrc.DataSource;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 * Servlet implementation class CreateQuizServlet
 */
@WebServlet("/CreateQuizServlet")
public class CreateQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizServlet() {
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
		// TODO Auto-generated method stub
		Connection con = null;
		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");
		String quizName = request.getParameter("quizName");
		int score = Integer.parseInt(request.getParameter("score"));
		String category = request.getParameter("category");
		Date createDate = new Date((new java.util.Date()).getTime());
		ArrayList<Question> questions = new ArrayList<Question>();
		int counter = 1;
		while(true){
			String questionType = request.getParameter("QuestionType_"+counter);
			if(questionType == null)
				break;
			String question = request.getParameter("Question_"+counter);
			ArrayList<Answer>ans = new ArrayList<Answer>();
			String[] answers = request.getParameterValues("Question_"+counter+"Answer");
			String[] correct = request.getParameterValues("Question_"+counter+"AnswerCorrect");
			int j = 0;
			for (int i = 0; i < answers.length; i++) {
				boolean isCorrect = false;
				if(correct != null && j<correct.length && correct[j] == "q"+counter+"a"+i){
					isCorrect = true;
				}
				ans.add(new Answer(answers[i], isCorrect));
			}
			questions.add(new Question(0, question, ans,Integer.parseInt(questionType)));
			counter++;
		}
		
		try {
			con = DataSource.getInstance().getConnection();
			QuizDaoImpl quizdao = new QuizDaoImpl(con);
			User user = am.getUser();
			Quiz quiz = new Quiz(0, quizName, user.getUserID(), score, "", createDate, questions);//TODO:Category
			quizdao.addUserCreatedQuiz(user.getUserID(), quiz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
	}

}
