package changeProfilePicture;

import java.sql.Connection;

import Database.UserDaoImpl;

public class changeProfPicDBaseCon {
	
	java.sql.Connection con;
	UserDaoImpl udi;
	
	public changeProfPicDBaseCon(Connection con){
		this.con = con;
	}
	
	public void changePicture(int userID, String picturePath){
		udi.setProfilePicture(userID, picturePath);
	}

}
