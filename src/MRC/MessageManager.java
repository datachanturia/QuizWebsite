package MRC;

import Model.Message;
import Model.User;
import java.sql.Connection;
import java.util.ArrayList;

import Database.MessageDaoImpl;

public class MessageManager {
	
	private MessageDaoImpl mdi;
	private int userID;
	
	public MessageManager(Connection con){
		
	}
	
	public void setConnection(java.sql.Connection conn){
		this.mdi = new MessageDaoImpl(conn);
	}
	
	public ArrayList<Message> getMessages(){
		return mdi.getUserMessages(userID);
	}
	
	public void sendMessage(Message mess){
		mdi.addMessage(mess);
	}
	
	public void setUserID(int userId){
		this.userID = userId;
	}
	
	

}
