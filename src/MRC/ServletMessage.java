package MRC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ULS.AccountManager;
import dataSrc.DataSource;

/**
 * Servlet implementation class ServletMessage
 */
@WebServlet("/ServletMessage")
public class ServletMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMessage() {
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
		
		MessageManager mm = (MessageManager) getServletContext().getAttribute("MessMan");
		int userID = Integer.parseInt(request.getParameter("usId"));
		mm.setUserID(userID);
		
		try {
			con = DataSource.getInstance().getConnection();
			mm.setConnection(con);

			rd = request.getRequestDispatcher("./MRC/MessageList.jsp");
			request.setAttribute("MessManager", mm);
			
			rd.forward(request, response);
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
