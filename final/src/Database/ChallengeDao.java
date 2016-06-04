package Database;

import java.util.ArrayList;

import Model.Challenge;

public interface ChallengeDao {
	
	public ArrayList<Challenge> getUserChallenges(int userID);
	public void addChallenge(Challenge challenge);
	public void deleteChallenge(int ChallengeID);
}
