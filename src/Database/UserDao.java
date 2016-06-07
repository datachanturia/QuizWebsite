package Database;

import Model.User;

public interface UserDao {

	public User getUser(String username, String password);
	public void addUser(User user);
	public void deleteUser(int userID);
	public void setAdmin(int userID, boolean isAdmin);
}
