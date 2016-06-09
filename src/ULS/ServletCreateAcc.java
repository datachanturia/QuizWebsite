package ULS;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.mysql.jdbc.Connection;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

import Database.MyDBInfo;

/**
 * Servlet implementation class ServletCreateAcc
 */
@WebServlet("/ServletCreateAcc")
public class ServletCreateAcc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCreateAcc() {
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
		Connection con = null;

		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");
		String usr = request.getParameter("username");
		String ema = request.getParameter("email");
		String pas = request.getParameter("password");

		RequestDispatcher rd;

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = getConnection();
			am.setConnection(con);

			if (!am.isValidMail(ema)) {
				rd = request.getRequestDispatcher("./logMenu/invalidMailing.html");
			} else if (am.accountExists(ema)) {
				rd = request.getRequestDispatcher("./logMenu/inUse.jsp");
			} else {
				am.createAccount(usr, ema, pas);
				request.setAttribute("accManager", am);
				if (am.getUser().isAdmin()) {
					rd = request.getRequestDispatcher("./adminLoggedIn/welcomeUser.jsp");
				} else {
					rd = request.getRequestDispatcher("./loggedIn/welcomeUser.jsp");
				}
			}

			rd.forward(request, response);
		} catch (SQLException | CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
	}

	// private DataSource datasource;
	//
	// public void init(ServletConfig config) throws ServletException {
	// try {
	// // Look up the JNDI data source only once at init time
	// System.out.println("araaaaa");
	// Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
	// datasource = (DataSource) ((InitialContext) envCtx)
	// .lookup("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER);
	//
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }

	private Connection getConnection() throws SQLException {
		// return (Connection) datasource.getConnection(MyDBInfo.MYSQL_USERNAME,
		// MyDBInfo.MYSQL_PASSWORD);
		return (Connection) DriverManager.getConnection("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER,
				MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);
	}

}
