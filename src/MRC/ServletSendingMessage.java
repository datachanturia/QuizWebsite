package MRC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.MessageDaoImpl;
import Database.QuizDaoImpl;
import Model.Message;
import Model.Quiz;
import ULS.AccountManager;
import dataSrc.DataSource;

/**
 * Servlet implementation class ServletSendingMessage
 */
@WebServlet("/ServletSendingMessage")
public class ServletSendingMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSendingMessage() {
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

		RequestDispatcher rd;

		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");

		int sender = Integer.parseInt(request.getParameter("sender"));
		int receiver = Integer.parseInt(request.getParameter("receiver"));
		String message = request.getParameter("message");
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		Message m = new Message(0, sender, receiver, message, "", "", date, false);

		try {

			con = DataSource.getInstance().getConnection();

			MessageDaoImpl mdi = new MessageDaoImpl(con);

			mdi.addMessage(m);

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
			
			rd = request.getRequestDispatcher("./MRC/SentMessage.jsp");
			

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
