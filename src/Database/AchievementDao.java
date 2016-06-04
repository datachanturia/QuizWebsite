package Database;

import java.util.ArrayList;

import Model.Achievement;

public interface AchievementDao {

	public ArrayList<Achievement> getuserAchievements(int userID);
	public void addUserAchievement(int userID, int AchievementID);
	public void addAchievement(Achievement achievement);
	public void deleteAchievement(int achievementID);
}
