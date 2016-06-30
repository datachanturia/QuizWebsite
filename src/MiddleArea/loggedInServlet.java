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
import ULS.AccountManager;
import dataSrc.DataSource;

/**
 * Servlet implementation class loggedInServlet
 */
@WebServlet("/loggedInServlet")
public class loggedInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loggedInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;

		RequestDispatcher rd;
		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");
		
		am.setLogStatus(true);

		try {
			con = DataSource.getInstance().getConnection();

			// ------------------------------------------
			QuizDaoImpl qdi = new QuizDaoImpl(con);

			request.setAttribute("accManager", am);

			ArrayList<Quiz> dayPopuLs = qdi.getDayPopularQuiz();
			ArrayList<Quiz> popQuizLs = qdi.getPopularQuiz();
			ArrayList<Quiz> newQuizLs = qdi.getNewQuiz();

			request.setAttribute("dayPopuLs", dayPopuLs);
			request.setAttribute("popQuizLs", popQuizLs);
			request.setAttribute("newQuizLs", newQuizLs);
			// --------------------------------------------------

			rd = request.getRequestDispatcher("./loggedIn/homeUser.jsp");

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
