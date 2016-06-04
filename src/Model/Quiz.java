package Model;

import java.util.ArrayList;

public class Quiz {
	
	private int quizID;
	private String quizname;
	private int authorID;
	private int score;
	private int category;//?
	private int creationDate;
	private ArrayList<Question> questions;
	
	public Quiz(int quizID, String quizname, int authorID, int creationDate) {
		super();
		this.quizID = quizID;
		this.quizname = quizname;
		this.authorID = authorID;
		this.creationDate = creationDate;
	}

	public int getQuizID() {
		return quizID;
	}

	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}

	public String getQuizname() {
		return quizname;
	}

	public void setQuizname(String quizname) {
		this.quizname = quizname;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(int creationDate) {
		this.creationDate = creationDate;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
}
