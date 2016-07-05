package MRC;

import java.sql.Connection;
import java.util.ArrayList;

import Database.ChallengeDaoImpl;
import Model.Challenge;

public class ChallengeManager {
	
	private ChallengeDaoImpl cdi;
	private int userID;
	private ArrayList<Challenge> challenges;
	
	public ChallengeManager(Connection con){
		
	}
	
	public void setConnection(java.sql.Connection conn){
		this.cdi = new ChallengeDaoImpl(conn);
	}
	
	public void deleteRequest(int challengeID){
		cdi.deleteChallenge(challengeID);
	}
	
	public void setUserID(int userId){
		this.userID = userId;
	}
	
	public int getUserID(){
		return this.userID;
	}
	
	public void setChallenges(ArrayList<Challenge> challenges){
		this.challenges = challenges;
	}
	
	public ArrayList<Challenge> getChallenges(){
		return this.challenges;
	}
}
