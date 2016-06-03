package Model;

public class Achievement {
	
	private int achievementID;
	private AchievementType achievementType;
	
	public Achievement(int achievementID, AchievementType achievementType) {
		this.achievementID = achievementID;
		this.achievementType = achievementType;
	}
	
	public int getAchievementID() {
		return achievementID;
	}
	public void setAchievementID(int achievementID) {
		this.achievementID = achievementID;
	}
	public AchievementType getAchievementType() {
		return achievementType;
	}
	public void setAchievementType(AchievementType achievementType) {
		this.achievementType = achievementType;
	}
	
	
	
}
