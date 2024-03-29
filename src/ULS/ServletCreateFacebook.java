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
 * Servlet implementation class ServletCreateFacebook
 */
@WebServlet("/ServletCreateFacebook")
public class ServletCreateFacebook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCreateFacebook() {
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
		String usr = request.getParameter("fusername");
		String ema = request.getParameter("femail");
		String pas = request.getParameter("fpassword");
		String pho = request.getParameter("fphoto");

		RequestDispatcher rd;

		try {

			con = DataSource.getInstance().getConnection();
			am.setConnection(con);
			QuizDaoImpl qdi = new QuizDaoImpl(con);

			if (!am.isValidMail(ema)) {
				rd = request.getRequestDispatcher("./logMenu/invalidMailing.html");
			} else if (am.accountExists(ema)) {
				rd = request.getRequestDispatcher("./logMenu/fbInUse.jsp");
			} else {
				am.createSocAccount(usr, ema, pas, pho);

				request.setAttribute("accManager", am);

				ArrayList<Quiz> dayPopuLs = qdi.getDayPopularQuiz();
				ArrayList<Quiz> popQuizLs = qdi.getPopularQuiz();
				ArrayList<Quiz> newQuizLs = qdi.getNewQuiz();

				request.setAttribute("dayPopuLs", dayPopuLs);
				request.setAttribute("popQuizLs", popQuizLs);
				request.setAttribute("newQuizLs", newQuizLs);
				
				am.setLogStatus(true);

				rd = request.getRequestDispatcher("./loggedIn/homeUser.jsp");

			}

			rd.forward(request, response);
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
