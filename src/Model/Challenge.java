package Model;

import java.sql.Date;

public class Challenge {
	
	private int challengeID;
	private int senderID;
	private int recieverID;
	private int quizID;
	private Date sendDate;
	private String senderName;
	private String quizName;
	
	public Challenge(int challengeID, int senderID, int recieverID, int quizID, Date sendDate, String senderName, String quizName) {
		super();
		this.challengeID = challengeID;
		this.senderID = senderID;
		this.recieverID = recieverID;
		this.quizID = quizID;
		this.sendDate = sendDate;
		this.senderName = senderName;
		this.quizName = quizName;
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
	
	public String getSenderName(){
		return this.senderName;
	}
	
	public void setSenderName(String senderName){
		this.senderName = senderName;
	}
	
	public String getQuizName(){
		return this.quizName;
	}
	
	public void setQuizName(String quizName){
		this.quizName = quizName;
	}
	
}
