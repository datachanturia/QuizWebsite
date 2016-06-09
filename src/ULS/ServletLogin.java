package ULS;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");
		String ema = request.getParameter("email");
		String pas = request.getParameter("password");

		RequestDispatcher rd;
		try {
			if (am.matchesAccount(ema, pas)) {
				rd = request.getRequestDispatcher("./logMenu/welcomeUser.jsp");
			} else {
				rd = request.getRequestDispatcher("./logMenu/informationIncorrect.html");
			}
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			System.out.println("ServletLogin.java --> ClassNotFoundException");;
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("ServletLogin.java --> SQLException");;
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			System.out.println("ServletLogin.java --> CloneNotSupportedException");;
			e.printStackTrace();
		}
	}

}
