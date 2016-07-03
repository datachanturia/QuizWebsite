package friends;

import java.sql.Connection;
import java.util.ArrayList;

import Database.UserDaoImpl;

public class friendsDatabaseConnector {
	java.sql.Connection con;
	UserDaoImpl udi;
	
	public friendsDatabaseConnector(Connection con) {
	}

	public void setConnection(java.sql.Connection con2) {
		this.con = con2;
		this.udi = new UserDaoImpl(con2);
	}
	
	ArrayList<Integer> getUserFriends(int userID){
		return udi.getUserFriends(userID);
	}
	
	public String getUserPhoto(int userID){
		return udi.getUserPhoto(userID);
	}
	
	public String getUserName(int userID){
		return udi.getUserName(userID);
	}
}
