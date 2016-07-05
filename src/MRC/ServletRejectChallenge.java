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

import Database.ChallengeDaoImpl;
import Database.QuizDaoImpl;
import Model.Quiz;
import ULS.AccountManager;
import dataSrc.DataSource;

/**
 * Servlet implementation class ServletRejectChallenge
 */
@WebServlet("/ServletRejectChallenge")
public class ServletRejectChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRejectChallenge() {
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

		int challengeID = Integer.parseInt(request.getParameter("challengeID"));
		int userID = am.getUser().getUserID();
		try {
			con = DataSource.getInstance().getConnection();
			
			ChallengeDaoImpl cdi = new ChallengeDaoImpl(con);
			cdi.deleteChallenge(challengeID);
			
			ChallengeManager cm = new ChallengeManager(con);
			cm.setUserID(userID);
			cm.setChallenges(cdi.getUserChallenges(userID));
			
			request.setAttribute("ChallManager", cm);

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

			rd = request.getRequestDispatcher("./MRC/ChallengeList.jsp");

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
