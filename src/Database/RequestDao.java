package Database;

import java.util.ArrayList;

import Model.Request;

public interface RequestDao {
	
	public ArrayList<Request> getUserRequests(int userID);
	public void addRequest(Request request);
	public void addFriend(int userID, int friendID);
	public void deleteRequest(int requestID);
}
