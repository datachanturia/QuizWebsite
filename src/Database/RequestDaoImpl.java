package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

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
			ResultSet rs = stmt.executeQuery("select requestID, senderID, receiverID, senddate, username "
					+ "from requests inner join users on senderID = userID where receiverID = " + userID + " and requests.isdelete = 0");
			while(rs.next()){
				Request r = new Request(rs.getInt("requestID"), rs.getInt("senderID"), rs.getInt("receiverID"), 
						rs.getDate("senddate"), rs.getString("username"));
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
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO requests (senderID, receiverID, "
					+ "senddate, isdelete) VALUES(?,?,?,?)");
			preparedStatement.setInt(1, request.getSenderID());
			preparedStatement.setInt(2, request.getReceiverID());
			preparedStatement.setDate(3, request.getSendDate());
			preparedStatement.setBoolean(4, false);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addFriend(int userID, int friendID){
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		try {
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO friends (userID, friendID, "
					+ "creationdate, isdelete) VALUES(?,?,?,?)");
			preparedStatement.setInt(1, userID);
			preparedStatement.setInt(2, friendID);
			preparedStatement.setDate(3, date);
			preparedStatement.setBoolean(4, false);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRequest(int requestID) {
		try {
			PreparedStatement prepst = con.prepareStatement("update Requests set isdelete = ? "
					+ "where requestID = " + requestID);
			prepst.setInt(1, 1);
			prepst.execute();
		} catch (SQLException e) {
			System.out.println("oh no!");
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Integer> sendRequestIDs(int userID) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select receiverID from requests where senderID = " + userID + " and isdelete = 0");
			while(rs.next()){
				ids.add(rs.getInt("receiverID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ids;
	}
	
	

}
