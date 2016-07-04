package MRC;

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
import Model.User;
import ULS.AccountManager;
import dataSrc.DataSource;

/**
 * Servlet implementation class ServletChallenge
 */
@WebServlet("/ServletChallenge")
public class ServletChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletChallenge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.getWriter().append("Served at: ").append(request.getContextPath());

		Connection con = null;

		RequestDispatcher rd;

		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");

		int userID = am.getUser().getUserID();
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		try {
			con = DataSource.getInstance().getConnection();
			
			UserDaoImpl udi = new UserDaoImpl(con);

			ArrayList<Integer> friendIds = udi.getUserFriends(userID);

			ArrayList<User> friends = new ArrayList<User>();
			for (int i = 0; i < friendIds.size(); i++) {
				User us = udi.getUserById(friendIds.get(i));
				if (us != null) {
					friends.add(us);
				}
			}

			request.setAttribute("mfriends", friends);
			
			request.setAttribute("quizID", quizID);

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

			rd = request.getRequestDispatcher("./MRC/FriendListForChallenge.jsp");

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
