package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Achievement;
import Model.AchievementType;
import Model.QuestionType;
import Model.Request;

public class AchievementDaoImpl implements AchievementDao {

	private Connection con;

	public AchievementDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Achievement> getuserAchievements(int userID) {
		ArrayList<Achievement> achiev = new ArrayList<Achievement>();
		System.out.println("HERE");
		try {
			Statement stmt = con.createStatement();
			System.out.println("HERE before select");
			ResultSet rs = stmt.executeQuery("select userID, achievement from UsersAchievement u inner join "
					 + "Achievements a on u.achievementID = a.achievementID "
					 + "where userID = " + userID + " and a.isdelete = 0 and u.isdelete = 0");
			System.out.println("HERE from select");
			while(rs.next()){
				Achievement ach = new Achievement(rs.getInt("achievement"),  AchievementType.values()[rs.getInt("userID")]);
				achiev.add(ach);
				System.out.println("HERE from wehre" + achiev.size());
			}
			System.out.println("achivs" + achiev.size());
		} catch (SQLException e) {
			System.out.println("ERROR DICK");
			e.printStackTrace();
		}
		
		return achiev;
	}

	@Override
	public void addUserAchievement(int userID, int AchievementID) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO UsersAchievement (userID, achievementID) VALUES(?,?)");
			preparedStatement.setInt(1, userID);
			preparedStatement.setInt(2, AchievementID);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addAchievement(Achievement achievement) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Achievement (achievement) VALUES(?)");
			preparedStatement.setInt(1, achievement.getAchievementType().ordinal());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAchievement(int achievementID) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement("update UsersAchievement set isdelete = ? "
					+ "where achievementID = " + achievementID);
			preparedStatement.setInt(1, 1);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
