package changeProfilePicture;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataSrc.DataSource;

/**
 * Servlet implementation class chProfilePicServlet
 */
@WebServlet("/chProfilePicServlet")
public class chProfilePicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chProfilePicServlet() {
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
		String picturePath = (String) request.getAttribute("pictureURL");
		RequestDispatcher rd;
		
		int userID = (int) request.getAttribute("userID");
		
		Connection con = DataSource.getInstance().getConnection();
		changeProfPicDBaseCon dbCon = new changeProfPicDBaseCon(con);
		dbCon.changePicture(userID, picturePath);
		
		rd = request.getRequestDispatcher("./loggedIn/homeUesr.jsp");
		
		rd.forward(request, response);
	}

}
