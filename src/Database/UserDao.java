package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.User;

public interface UserDao {

	public User getUser(String email, String password);

	public void addUser(User user);

	public boolean existsUser(String email);

	public void deleteUser(int userID);

	public void setAdmin(int userID, boolean isAdmin);

	public ArrayList<Integer> getUserFriends(int userID);

	public String getUserPhoto(int UserID);

	public String getUserName(int userID);

	public ArrayList<Integer> getUserIDs(String username);

	public void setProfilePicture(int userID, String picturePath);

	public boolean setPass(int userID, String curPas, String newPas) throws CloneNotSupportedException;
	
	public User getUserById(int userId);
}
