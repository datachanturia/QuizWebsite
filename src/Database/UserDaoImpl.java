package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
						rs.getBoolean("isadmin"));
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
					"INSERT INTO Users (username, pass, mail, photo, creationdate, isadmin) VALUES(?,?,?,?,?,?)");

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getMail());
			preparedStatement.setString(4, user.getPhoto());
			preparedStatement.setDate(5, user.getCreateDate());
			preparedStatement.setBoolean(6, user.isAdmin());

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
}
