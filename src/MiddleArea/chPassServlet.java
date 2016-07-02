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
import Database.UserDaoImpl;
import Model.Quiz;
import ULS.AccountManager;
import changeProfilePicture.changeProfPicDBaseCon;
import dataSrc.DataSource;

/**
 * Servlet implementation class chPassServlet
 */
@WebServlet("/chPassServlet")
public class chPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public chPassServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");

		String curPass = request.getParameter("curPas");
		String newPas1 = request.getParameter("newPas1");
		String newPas2 = request.getParameter("newPas2");
		RequestDispatcher rd = null;

		Connection con = null;

		try {
			con = DataSource.getInstance().getConnection();

			UserDaoImpl udi = new UserDaoImpl(con);

			if (newPas1.equals(newPas2) && udi.setPass(am.getUser().getUserID(), curPass, newPas1)) {
				// ------------------------------------------
				QuizDaoImpl qdi = new QuizDaoImpl(con);

				ArrayList<Quiz> dayPopuLs = qdi.getDayPopularQuiz();
				ArrayList<Quiz> popQuizLs = qdi.getPopularQuiz();
				ArrayList<Quiz> newQuizLs = qdi.getNewQuiz();

				request.setAttribute("dayPopuLs", dayPopuLs);
				request.setAttribute("popQuizLs", popQuizLs);
				request.setAttribute("newQuizLs", newQuizLs);
				// --------------------------------------------------

				rd = request.getRequestDispatcher("./loggedIn/homeUser.jsp");
			} else {
				rd = request.getRequestDispatcher("./ChangePassFailed.jsp");
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
