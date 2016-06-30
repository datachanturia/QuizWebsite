package MRC;

import java.sql.Connection;
import java.util.ArrayList;

import Database.MessageDaoImpl;
import Database.RequestDaoImpl;
import Model.Request;

public class RequestManager {
	
	private RequestDaoImpl rdi;
	private int userID;
	
	public RequestManager(Connection con){
		
	}
	
	public void setConnection(java.sql.Connection conn){
		this.rdi = new RequestDaoImpl(conn);
	}
	
	public ArrayList<Request> getRequests(){
		return rdi.getUserRequests(userID);
	}
	
	public void addRequest(Request request){
		rdi.addRequest(request);
	}
	
	public void deleteRequest(int requestID){
		rdi.deleteRequest(requestID);
	}
	
	public void setUserID(int userId){
		this.userID = userId;
	}
	
}
