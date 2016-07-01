package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.User;
import dataSrc.MyDBInfo;

public class UserDaoImpl implements UserDao {

	private Connection con;

	public UserDaoImpl(Connection con) {
		this.con = con;
	}

	public User getUser(String email, String password) {
		try {

			Statement stmt = con.createStatement();

			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

			PreparedStatement prdtmt = con
					.prepareStatement("SELECT * FROM Users where mail = ? and pass = ? and isdelete = ?");

			prdtmt.setString(1, email);
			prdtmt.setString(2, password);
			prdtmt.setBoolean(3, false);

			ResultSet rs = prdtmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("pass"),
						rs.getString("mail"), rs.getString("photo"), rs.getDate("creationdate"),
						rs.getBoolean("isadmin"), rs.getBoolean("isSoc"));
				return user;
			}
		} catch (SQLException e) {
			System.out.println("getUser(String email, String password) --> method failed");
			e.printStackTrace();
		}
		return null;
	}

	public boolean existsUser(String email) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

			PreparedStatement prdtmt = con.prepareStatement("SELECT * FROM Users where mail = ? and isdelete = ?");
			prdtmt.setString(1, email);
			prdtmt.setBoolean(2, false);

			ResultSet rs = prdtmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println("getUser(String email, String password) --> method failed");
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void addUser(User user) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"INSERT INTO Users (username, pass, mail, photo, creationdate, isadmin, isSoc) VALUES(?,?,?,?,?,?,?)");

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getMail());
			preparedStatement.setString(4, user.getPhoto());
			preparedStatement.setDate(5, user.getCreateDate());
			preparedStatement.setBoolean(6, user.isAdmin());
			preparedStatement.setBoolean(7, user.isSoc());

			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(int userID) {
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("update Users set isdelete = ? " + "where userID = " + userID);
			preparedStatement.setInt(1, 1);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setAdmin(int userID, boolean isAdmin) {
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("update Users set isadmin = ? " + "where userID = " + userID);
			preparedStatement.setBoolean(1, isAdmin);

			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Integer> getUserFriends(int userID) {
		ArrayList<Integer> friends = new ArrayList<Integer>();
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("use " + MyDBInfo.MYSQL_DATABASE_NAME);
			ResultSet res = stm
					.executeQuery("select friendID from Friends where userID = " + userID + "and isdelete = 0");
			while (res.next()) {
				friends.add(res.getInt("friendID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return friends;
	}

	public String getUserPhoto(int userID) {
		String photo = "";
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("use " + MyDBInfo.MYSQL_DATABASE_NAME);
			ResultSet res = stm.executeQuery("select photo from Users where userID = " + userID);
			while (res.next()) {
				photo = res.getString("photo");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return photo;
	}

	public String getUserName(int userID) {
		String userName = "";
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("use " + MyDBInfo.MYSQL_DATABASE_NAME);
			ResultSet res = stm.executeQuery("select username from Users where userID = " + userID);
			while (res.next()) {
				userName = res.getString("username");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userName;
	}

	public ArrayList<Integer> getUserIDs(String username) {
		ArrayList<Integer> users = new ArrayList<Integer>();

		try {
			Statement stm = con.createStatement();
			stm.executeQuery("use " + MyDBInfo.MYSQL_DATABASE_NAME);
			ResultSet res = stm
					.executeQuery("select userID from Users where username = \"" + username + "\" and isdelete = 0");
			while (res.next()) {
				users.add(res.getInt("userID"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return users;
	}

	public void setProfilePicture(int userID, String picturePath) {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement("update Users set photo = ? " + "where userID = " + userID);
			preparedStatement.setString(1, picturePath);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
