package Database;

import java.util.ArrayList;

import Model.Message;

public interface MessageDao {

	public ArrayList<Message> getUserMessages(int userID);
	public void addMessage(Message message);
	public void deleteMessage(int messageID);
	public void read(int messageID);
}
