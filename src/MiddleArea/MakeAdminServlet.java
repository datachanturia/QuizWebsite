package MiddleArea;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.UserDaoImpl;
import dataSrc.DataSource;

/**
 * Servlet implementation class MakeAdminServlet
 */
@WebServlet("/MakeAdminServlet")
public class MakeAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakeAdminServlet() {
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
		Connection con = null;

		RequestDispatcher rd;

		String mail = request.getParameter("userMail");

		try {
			con = DataSource.getInstance().getConnection();

			UserDaoImpl udi = new UserDaoImpl(con);
			udi.MakeAdmin(mail);

			rd = request.getRequestDispatcher("./Admin/MadeAdminSuccesfully.jsp");

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
