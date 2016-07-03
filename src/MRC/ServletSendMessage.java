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
import Model.Quiz;
import ULS.AccountManager;
import dataSrc.DataSource;

/**
 * Servlet implementation class ServletSendMessage
 */
@WebServlet("/ServletSendMessage")
public class ServletSendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSendMessage() {
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
		MessageManager mm = new MessageManager(null);
		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");

		int userID = am.getUser().getUserID();
		mm.setUserID(userID);

		request.setAttribute("sender", userID);
		request.setAttribute("receiver", request.getParameter("receiver"));
		request.setAttribute("receiverName", request.getParameter("receiverName"));

		try {
			con = DataSource.getInstance().getConnection();
			mm.setConnection(con);

			request.setAttribute("MessManager", mm);

			rd = request.getRequestDispatcher("./MRC/NewMessage.jsp");

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
