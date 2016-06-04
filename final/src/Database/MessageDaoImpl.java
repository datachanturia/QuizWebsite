package Database;

import java.sql.Connection;
import java.util.ArrayList;

import Model.Message;

public class MessageDaoImpl implements MessageDao{

	public MessageDaoImpl(Connection con) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Message> getUserMessages(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMessage(int messageID) {
		// TODO Auto-generated method stub
		
	}

}
