package friends;

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

import dataSrc.DataSource;

/**
 * Servlet implementation class myFriends
 */
@WebServlet("/myFriends")
public class myFriends extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myFriends() {
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
		// TODO Auto-generated method stub
		Connection con;
		con = DataSource.getInstance().getConnection();
		friendsDatabaseConnector fdc = new friendsDatabaseConnector(con);
		
		fdc.setConnection(con);
		
		//SHESACVLELIA
		ArrayList<Integer> friends = fdc.getUserFriends(1);
		
		request.setAttribute("userFriends", friends);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("./myFriends/user/userFriends.jsp");
		rd.forward(request, response);
		
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
			}
		
	}
}
