package View;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Database.QuestionDaoImpl;
import Database.QuizDaoImpl;
import Model.Question;
import Model.Quiz;
import dataSrc.DataSource;
import java.util.Collections;
import java.util.Date;

/**
 * Servlet implementation class AttemptQuizServlet
 */
@WebServlet("/AttemptQuizServlet")
public class AttemptQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttemptQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("Quiz/MultPage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		int quizid = Integer.parseInt(request.getParameter("quizID"));
		ArrayList<Question> questions = null;
		Quiz quiz = null;
		try {
			con = DataSource.getInstance().getConnection();
			QuizDaoImpl quizdao = new QuizDaoImpl(con);
			QuestionDaoImpl qd = new QuestionDaoImpl(con);
			quiz = quizdao.getQuiz(quizid);
			questions = qd.getQuizQuestions(quizid);
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
		if(quiz.isRandom()){
			Collections.shuffle(questions);
		}
		HttpSession session = request.getSession();
		session.setAttribute("quiz", quiz);
		session.setAttribute("questions", questions);
		session.setAttribute("currindex", 1);
		session.setAttribute("startTime", (new Date()).getTime());
		if(Boolean.parseBoolean(request.getParameter("isMultiple"))){
		request.getSession(false).setAttribute("quizID", request.getParameter("quizID"));
			response.sendRedirect("Quiz/MultPage.jsp");
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("Quiz/AttemptQuiz.jsp");
			rd.forward(request, response);
		}
		
	}

}
