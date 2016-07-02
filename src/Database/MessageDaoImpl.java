package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Message;

public class MessageDaoImpl implements MessageDao {

	private Connection con;

	public MessageDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Message> getUserMessages(int userID) {
		ArrayList<Message> messages = new ArrayList<Message>();

		try {
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery("select messageID, message, senderID, receiverID, senddate, "
					+ "isread, m.username as senderName, u.username as receiveName from "
					+ "(select messageID, message, senderID, receiverID, senddate, isread, username from "
					+ "messages inner join users on senderID = userID where " + "(senderID = " + userID
					+ " or receiverID = " + userID + ") and messages.isdelete = 0) "
					+ "m inner join users u on m.receiverID = u.userId order by senddate desc");
			
			while (result.next()) {
<<<<<<< HEAD
				Message message = new Message(result.getInt("m.messageID"), result.getInt("m.senderID"),
						result.getInt("m.receiverID"), result.getString("m.message"), result.getString("senderName"),
						result.getString("receiveName"), result.getDate("sendDate"), result.getBoolean("isread"));
=======
				Message message = new Message(result.getInt("messageID"), result.getInt("senderID"),
						result.getInt("receiverID"), result.getString("message"), result.getString("senderName"),
						result.getString("receiverName"), result.getDate("sendDate"), result.getBoolean("isread"));
>>>>>>> aa0a27f9c353bf255d8779027a34d6d7d6e56458
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
			PreparedStatement prepst = con.prepareStatement(
					"insert into Messages (message, senderID, receiverID, sendDate) " + " values (?,?,?,?)");

			prepst.setString(1, message.getMessage());
			prepst.setInt(2, message.getSenderID());
			prepst.setInt(3, message.getReceiverID());
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
			PreparedStatement prepst = con
					.prepareStatement("update Messages set isdelete = ? " + "where messageID = " + messageID);
			prepst.setInt(1, 1);
			prepst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
