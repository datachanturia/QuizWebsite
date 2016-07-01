package changeProfilePicture;

import java.sql.Connection;

import Database.UserDaoImpl;

public class changeProfPicDBaseCon {

	private UserDaoImpl udi;

	public changeProfPicDBaseCon(Connection con) {
		udi = new UserDaoImpl(con);
	}

	public void changePicture(int userID, String picturePath) {
		udi.setProfilePicture(userID, picturePath);
	}

}
