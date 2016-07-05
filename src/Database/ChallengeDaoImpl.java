package Database;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Challenge;
import Model.Request;

public class ChallengeDaoImpl implements ChallengeDao {

	private Connection con;
	
	public ChallengeDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Challenge> getUserChallenges(int userID) {
		ArrayList<Challenge> challenges = new ArrayList<Challenge>();
		
		try {
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery("select challengeID, senderID, receiverID, c.quizID, senddate, username, quizname"
					+ " from (select challengeID, senderID, receiverID, quizID, senddate, username from "
					+ "Challenges inner join users on senderID = userID where "
					+ "receiverID = " + userID + " and challenges.isdelete = 0) c inner join quiz q on "
					+ "c.quizID = q.quizID where q.isdelete = 0");
			while(result.next()){
				Challenge challenge = new Challenge(result.getInt("challengeID"), result.getInt("senderID"), result.getInt("receiverID"), 
						result.getInt("quizID"), result.getDate("senddate"), result.getString("username"), result.getString("quizname"));
				challenges.add(challenge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return challenges;
	}

	@Override
	public void addChallenge(Challenge challenge) {
		try {
			PreparedStatement prepst = con.prepareStatement("insert into Challenges (senderID,receiverID,quizID,senddate) values (?,?,?,?)");
			prepst.setInt(1, challenge.getSenderID());
			prepst.setInt(2, challenge.getRecieverID());
			prepst.setInt(3, challenge.getQuizID());
			prepst.setDate(4, challenge.getSendDate());
			prepst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteChallenge(int challengeID) {
		try {
			PreparedStatement prepst = con.prepareStatement("update Challenges set isdelete = ? "
					+ "where challengeID = " + challengeID);
			prepst.setInt(1, 1);
			prepst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
