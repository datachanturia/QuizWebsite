package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.User;
import ULS.Encrypt;
import dataSrc.MyDBInfo;

public class UserDaoImpl implements UserDao {

	private Connection con;

	public UserDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
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

	@Override
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
			ResultSet res = stm.executeQuery("select friendID, userID from Friends where (userID = " + userID
					+ " or friendID = " + userID + ") and isdelete = 0");

			while (res.next()) {
				int idd;
				if (res.getInt("friendID") == userID) {
					idd = res.getInt("userID");
				} else {
					idd = res.getInt("friendID");
				}
				friends.add(idd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return friends;
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
	public boolean setPass(int userID, String curPas, String newPas) throws CloneNotSupportedException {

		PreparedStatement preparedStatement;
		try {
			Encrypt en = new Encrypt();
			String curFinalPass = en.GenerationMode(curPas);
			String newFinalPass = en.GenerationMode(newPas);

			Statement stm = con.createStatement();
			stm.executeQuery("use " + MyDBInfo.MYSQL_DATABASE_NAME);
			ResultSet res = stm.executeQuery("select pass from Users where userID = \"" + userID + "\"");
			String recCurPas = "";
			while (res.next()) {
				recCurPas = res.getString("pass");
			}

			if (recCurPas.equals(curFinalPass)) {

				preparedStatement = con.prepareStatement("update Users set pass = ? " + "where userID = " + userID);
				preparedStatement.setString(1, newFinalPass);
				preparedStatement.execute();

				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User getUserById(int userId) {
		try {

			Statement stmt = con.createStatement();

			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
			PreparedStatement prdtmt = con.prepareStatement("SELECT * FROM Users where userID = ? and isdelete = 0");
			prdtmt.setInt(1, userId);

			ResultSet rs = prdtmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("pass"),
						rs.getString("mail"), rs.getString("photo"), rs.getDate("creationdate"),
						rs.getBoolean("isadmin"), rs.getBoolean("isSoc"));

				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void BanByMail(String mail) {
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("update Users set isdelete = 1 " + "where mail = \"" + mail + "\"");
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void MakeAdmin(String mail) {
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("update Users set isadmin = 1 " + "where mail = \"" + mail + "\"");
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
