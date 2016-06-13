package ULS;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;

		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");
		String usr = request.getParameter("username");
		String ema = request.getParameter("email");
		String pas = request.getParameter("password");
		String pho = request.getParameter("photo");

		RequestDispatcher rd;

		try {

			con = DataSource.getInstance().getConnection();
			am.setConnection(con);

			if (!am.isValidMail(ema)) {
				rd = request.getRequestDispatcher("./logMenu/invalidMailing.html");
			} else if (am.accountExists(ema)) {
				rd = request.getRequestDispatcher("./logMenu/inUse.jsp");
			} else {
				am.createSocAccount(usr, ema, pas, pho);

				request.setAttribute("accManager", am);
				if (am.getUser().isAdmin()) {
					rd = request.getRequestDispatcher("./adminLoggedIn/welcomeUser.jsp");
				} else {

					rd = request.getRequestDispatcher("./loggedIn/welcomeUser.jsp");
				}
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

}
