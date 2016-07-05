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

import Database.PostDaoImpl;
import Database.QuizDaoImpl;
import Model.Quiz;
import ULS.AccountManager;
import dataSrc.DataSource;

/**
 * Servlet implementation class ServletPost
 */
@WebServlet("/ServletPost")
public class ServletPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPost() {
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

		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");
		String message = request.getParameter("message");

		RequestDispatcher rd;

		Connection con = null;

		try {
			con = DataSource.getInstance().getConnection();
			PostDaoImpl pdi = new PostDaoImpl(con);
			pdi.addPost(message, am.getUser().getUserID(), am.getUser().getUsername());

			rd = request.getRequestDispatcher("./Admin/PostedSuccessfully.jsp");

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
