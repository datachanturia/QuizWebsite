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
import javax.sql.DataSource;

import com.mysql.jdbc.Connection;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

import Database.MyDBInfo;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLogin() {
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
		String ema = request.getParameter("email");
		String pas = request.getParameter("password");

		RequestDispatcher rd;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = getConnection();
			am.setConnection(con);

			if (am.matchesAccount(ema, pas)) {
				if(am.getUser().isAdmin()){
					rd = request.getRequestDispatcher("./adminLoggedIn/welcomeUser.jsp");
				}else{
					rd = request.getRequestDispatcher("./loggedIn/welcomeUser.jsp");
				}
				request.setAttribute("accManager", am);
			} else {
				rd = request.getRequestDispatcher("./logMenu/informationIncorrect.html");
			}
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
	}

//	private DataSource datasource;
//
//	public void init(ServletConfig config) throws ServletException {
//		try {
//			// Look up the JNDI data source only once at init time
//			System.out.println("araaaaa");
//			Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
//			datasource = (DataSource) ((InitialContext) envCtx)
//					.lookup("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER);
//
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	private Connection getConnection() throws SQLException {
		// return (Connection) datasource.getConnection(MyDBInfo.MYSQL_USERNAME,
		// MyDBInfo.MYSQL_PASSWORD);
		return (Connection) DriverManager.getConnection("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER,
				MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);
	}

}
