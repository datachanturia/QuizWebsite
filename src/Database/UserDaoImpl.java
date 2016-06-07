package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.User;

public class UserDaoImpl implements UserDao {

	private Connection con;

	public UserDaoImpl(Connection con) {
		this.con = con;
	}

	public User getUser(String username, String password) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select * from Users where username = " + username + "and pass = " + password);
			while (rs.next()) {
				User user = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("pass"),
						rs.getString("mail"), rs.getString("photo"), rs.getDate("creationdate"),
						rs.getBoolean("isadmin"), rs.getBoolean("isdelete"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addUser(User user) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"INSERT INTO Users (username, pass, mail, photo, creationdate) VALUES(?,?,?,?,?)");

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getMail());
			preparedStatement.setString(4, user.getPhoto());
			preparedStatement.setDate(5, user.getCreateDate());

			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(int userID) {
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("update Users set isdelete = ? " + "where answerID = " + userID);
			preparedStatement.setInt(1, 1);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
