package Database;

import java.sql.Connection;
import java.util.ArrayList;

import Model.Achievement;

public class AchievementDaoImpl implements AchievementDao {

	public AchievementDaoImpl(Connection con) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ArrayList<Achievement> getuserAchievements(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserAchievement(int userID, int AchievementID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAchievement(Achievement achievement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAchievement(int achievementID) {
		// TODO Auto-generated method stub

	}

}
