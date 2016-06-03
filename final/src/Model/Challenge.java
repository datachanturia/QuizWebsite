package Model;

import java.sql.Date;

public class Challenge {
	
	private int challengeID;
	private int senderID;
	private int recieverID;
	private int quizID;
	private Date sendDate;
	private boolean isread;
	
	public Challenge(int challengeID, int senderID, int recieverID, int quizID, Date sendDate, boolean isread) {
		super();
		this.challengeID = challengeID;
		this.senderID = senderID;
		this.recieverID = recieverID;
		this.quizID = quizID;
		this.sendDate = sendDate;
		this.isread = isread;
	}

	public int getChallengeID() {
		return challengeID;
	}

	public void setChallengeID(int challengeID) {
		this.challengeID = challengeID;
	}

	public int getSenderID() {
		return senderID;
	}

	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}

	public int getRecieverID() {
		return recieverID;
	}

	public void setRecieverID(int recieverID) {
		this.recieverID = recieverID;
	}

	public int getQuizID() {
		return quizID;
	}

	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public boolean isread() {
		return isread;
	}

	public void setIsread(boolean isread) {
		this.isread = isread;
	}
	
	
	
	
	
	
}
