package MRC;

import java.sql.Connection;
import java.util.ArrayList;

import Database.MessageDaoImpl;
import Database.RequestDaoImpl;
import Model.Message;
import Model.Request;

public class RequestManager {
	
	private RequestDaoImpl rdi;
	private int userID;
	private ArrayList<Request> requests;
	
	public RequestManager(Connection con){
		
	}
	
	public void setConnection(java.sql.Connection conn){
		this.rdi = new RequestDaoImpl(conn);
	}
	
	public void deleteRequest(int requestID){
		rdi.deleteRequest(requestID);
	}
	
	public void setUserID(int userId){
		this.userID = userId;
	}
	
	public int getUserID(){
		return this.userID;
	}
	
	public void setRequests(ArrayList<Request> requests){
		this.requests = requests;
	}
	
	public ArrayList<Request> getRequests(){
		return this.requests;
	}
	
}
