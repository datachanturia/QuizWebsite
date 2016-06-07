package ULS;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountManager am = (AccountManager) getServletContext().getAttribute("AccMan");
		String usr = request.getParameter("username");
		String pas = request.getParameter("password");

		RequestDispatcher rd;
		if (am.accountExists(usr)) {
			rd = request.getRequestDispatcher("logMenu/inUse.jsp");
		} else {
			am.createAccount(usr, pas);
			rd = request.getRequestDispatcher("logMenu/welcomeUser.jsp");
		}

		rd.forward(request, response);
	}

}
