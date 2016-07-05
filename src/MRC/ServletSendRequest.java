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

import Database.QuizDaoImpl;
import Database.RequestDaoImpl;
import Model.Quiz;
import Model.Request;
import ULS.AccountManager;
import dataSrc.DataSource;

/**
 * Servlet implementation class ServletSendRequest
 */
@WebServlet("/ServletSendRequest")
public class ServletSendRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSendRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Connection con = null;

		RequestDispatcher rd;

		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");

		int sender = am.getUser().getUserID();
		int receiver = Integer.parseInt(request.getParameter("receiver"));
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		Request r = new Request(0, sender, receiver, date, "");

		try {

			con = DataSource.getInstance().getConnection();

			RequestDaoImpl rdi = new RequestDaoImpl(con);

			rdi.addRequest(r);

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
			
			rd = request.getRequestDispatcher("./MRC/SentRequest.jsp");
			

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
