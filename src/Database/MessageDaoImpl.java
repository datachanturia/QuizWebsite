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
			ResultSet result = st.executeQuery("select m.messageID, m.message, m.senderID, m.recieverID, "
					+ "m.senddate, m.isread, m.username, u.username from "
					+ "(select messageID, message, senderID, recieverID, senddate, isread, username from "
					+ "messages inner join users on senderID = userID where (senderID = " + userID + " or "
							+ "recieverID = " + userID + ") and isdelete = 0) m inner join users u on "
									+ "m.recieverID = u.userID");
			while(result.next()){
				Message message = new Message(result.getInt("m.messageID"), result.getInt("m.senderID"), result.getInt("m.recieverID"), 
						result.getString("m.message"), result.getString("m.username"), result.getString("u.username"), 
						result.getDate("sendDate"), result.getBoolean("isread"));
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
			PreparedStatement prepst = con.prepareStatement("insert into Messages (message, senderID, recieverID, sendDate) "
					+ " values (?,?,?,?)");
			
			prepst.setString(1, message.getMessage());
			prepst.setInt(2, message.getSenderID());
			prepst.setInt(3, message.getRecieverID());
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
