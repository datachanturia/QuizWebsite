package seFriends;

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

import Database.UserDao;
import Database.UserDaoImpl;
import ULS.AccountManager;
import dataSrc.DataSource;

/**
 * Servlet implementation class searchFriendsServlet
 */
@WebServlet("/searchFriendsServlet")
public class searchFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public searchFriendsServlet() {
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

		String username = request.getParameter("username");
		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");

		Connection con = null;

		con = DataSource.getInstance().getConnection();

		UserDaoImpl user = new UserDaoImpl(con);

		ArrayList<Integer> users = user.getUserIDs(username);

		RequestDispatcher rd;
		request.setAttribute("accManager", am);

		if (users == null || users.size() == 0) {
			rd = request.getRequestDispatcher("./searchFriends/user/searchUserNotFound.jsp");
		} else {
			request.setAttribute("users", users);
			rd = request.getRequestDispatcher("./searchFriends/user/searchDoneUser.jsp");
		}

		rd.forward(request, response);

		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
			}
	}
}