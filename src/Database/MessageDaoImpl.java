package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Message;

public class MessageDaoImpl implements MessageDao{

	private Connection con;
	
	public MessageDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Message> getUserMessages(int userID) {
		ArrayList<Message> messages = new ArrayList<Message>();
		
		try {
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery("select * from((select messageID, message, senderName, recieverName, senddate, "
					+ "isread from Messages join Users on Messages.senderId = Users.userID where Users.userID =" + userID 
					+ " and isdelete = 0) union (select messageID, message, senderName, recieverName, senddate,"
					+ "isread from Messages join Users on Messages.recieverID = Users.userID "
					+ "where Users.userID =" + userID + " and isdelete = 0)) order by senddate DESC");
			while(result.next()){
				Message message = new Message(result.getInt("messageID"), result.getString("message"), result.getString("senderName"), 
						result.getString("recieverName"), result.getDate("sendDate"), result.getBoolean("isread"));
				messages.add(message);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return messages;
	}

	@Override
	public void addMessage(Message message) {
		try {
			PreparedStatement prepst = con.prepareStatement("insert into Messages (message,senderName,recieverName,sendDate) "
					+ " values (?,?,?,?)");
			
			Statement stForUsers = con.createStatement();
			ResultSet result = stForUsers.executeQuery("select userID from Users where username = " + message.getSenderName());
			String sender = result.getString("userID");
			
			result = stForUsers.executeQuery("select userID from Users where username = " + message.getRecieverName());
			String reciever = result.getString("userID");
			
			prepst.setString(1, message.getMessage());
			prepst.setString(2, sender);
			prepst.setString(3, reciever);
			prepst.setDate(4, message.getSendDate());
			prepst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteMessage(int messageID) {
		try {
			PreparedStatement prepst = con.prepareStatement("update Messages set isdelete = ? "
					+ "where messageID = " + messageID);
			prepst.setInt(1, 1);
			prepst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
