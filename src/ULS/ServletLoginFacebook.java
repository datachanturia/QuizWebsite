package ULS;

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
 * Servlet implementation class ServletLoginFacebook
 */
@WebServlet("/ServletLoginFacebook")
public class ServletLoginFacebook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLoginFacebook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection con = null;

		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");
		String ema = request.getParameter("femail");
		String pas = request.getParameter("fpassword");

		RequestDispatcher rd;
		try {
			con = DataSource.getInstance().getConnection();
			am.setConnection(con);
			QuizDaoImpl qdi = new QuizDaoImpl(con);

			if (am.matchesSocAccount(ema, pas)) {
				ArrayList<Quiz> dayPopuLs = qdi.getDayPopularQuiz();
				ArrayList<Quiz> popQuizLs = qdi.getPopularQuiz();
				ArrayList<Quiz> newQuizLs = qdi.getNewQuiz();

				request.setAttribute("dayPopuLs", dayPopuLs);
				request.setAttribute("popQuizLs", popQuizLs);
				request.setAttribute("newQuizLs", newQuizLs);

				rd = request.getRequestDispatcher("./adminLoggedIn/welcomeUser.jsp");

				rd = request.getRequestDispatcher("./loggedIn/welcomeUser.jsp");

				request.setAttribute("accManager", am);
			} else {
				rd = request.getRequestDispatcher("./logMenu/informationIncorrect.html");
			}
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
