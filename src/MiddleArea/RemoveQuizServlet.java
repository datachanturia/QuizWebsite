package MiddleArea;

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

import Database.QuizDaoImpl;
import Model.Quiz;
import dataSrc.DataSource;

/**
 * Servlet implementation class RemoveQuizServlet
 */
@WebServlet("/RemoveQuizServlet")
public class RemoveQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveQuizServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String quizName = request.getParameter("quizName");
		String quizType = request.getParameter("quizCategory");

		RequestDispatcher rd;

		Connection con = null;

		try {
			con = DataSource.getInstance().getConnection();

			// ------------------------------------------
			QuizDaoImpl qdi = new QuizDaoImpl(con);

			String prepStatement;

			if (quizName.length() > 0) {
				prepStatement = "select * from Quiz where quizname = \"" + quizName + "\" " + "and isdelete = 0 and category = \""
						+ quizType + "\"";
			} else
				prepStatement = "select * from Quiz where category = \"" + quizType + "\" and isdelete = 0";

			ArrayList<Quiz> quizzes = qdi.getQuizes(prepStatement);

			request.setAttribute("quizzes", quizzes);

			if (quizzes.size() > 0) {
				rd = request.getRequestDispatcher("./RemoveQuiz/RemoveQuizzesDoneSearch.jsp");
			} else
				rd = request.getRequestDispatcher("./RemoveQuiz/RemoveQuizzesNFSearch.jsp");

		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		rd.forward(request, response);
	}

}
