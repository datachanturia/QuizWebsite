package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Answer;
import Model.Question;
import Model.Request;

public class RequestDaoImpl implements RequestDao {

	private Connection con;
	
	public RequestDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Request> getUserRequests(int userID) {
		ArrayList<Request> requests = new ArrayList<Request>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from requests where senderID = "+userID+" or receiverID = "+userID);
			while(rs.next()){
				Request r = new Request(rs.getInt("requestID"), rs.getInt("senderID"), rs.getInt("receiverID"),rs.getDate("senddate"),rs.getBoolean("isread"));
				requests.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public void addRequest(Request request) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO requests (senderID,receiverID,"
					+ "senddate,isdeleted) VALUES(?,?,?,?)");
			preparedStatement.setInt(1, request.getSenderID());
			preparedStatement.setInt(2, request.getRecieverID());
			preparedStatement.setDate(3, request.getSendDate());
			preparedStatement.setBoolean(4, false);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRequest(int requestID) {
		// TODO Auto-generated method stub

	}

}