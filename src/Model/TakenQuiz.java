package Model;

import java.sql.Date;

public class TakenQuiz {
	
	private int userID;
	private int quizID;
	private Date takeDate;
	private int score;
	private long quiztime;
	
	public TakenQuiz(int userID, int quizID, Date takeDate, int score) {
		super();
		this.userID = userID;
		this.quizID = quizID;
		this.takeDate = takeDate;
		this.score = score;
	}
	
	
	
	public TakenQuiz(int userID, int quizID, Date takeDate, int score, long quiztime) {
		this.userID = userID;
		this.quizID = quizID;
		this.takeDate = takeDate;
		this.score = score;
		this.quiztime = quiztime;
	}



	public TakenQuiz(Date takeDate, int score,long quiztime) {
		super();
		this.takeDate = takeDate;
		this.score = score;
		this.quiztime = quiztime;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getQuizID() {
		return quizID;
	}

	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}

	public Date getTakeDate() {
		return takeDate;
	}

	public void setTakeDate(Date takeDate) {
		this.takeDate = takeDate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}



	public long getQuiztime() {
		return quiztime;
	}



	public void setQuiztime(long quiztime) {
		this.quiztime = quiztime;
	}
	
	
	

}
