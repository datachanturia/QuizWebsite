package Model;

import java.util.ArrayList;
import java.sql.Date;

public class Quiz {

	private int quizID;
	private String quizname;
	private int authorID;
	private int score;
	private String category;
	private Date creationDate;
	private ArrayList<Question> questions;
	private String description;
	private boolean random;
	private boolean multiple_page;
	private boolean immediate_correction;

	public Quiz(int quizID, String quizname, int authorID, int score, String category, Date creationDate,
			ArrayList<Question> questions,String description,boolean random,boolean multiple_page,boolean immediate_correction) {
		super();
		this.quizID = quizID;
		this.quizname = quizname;
		this.authorID = authorID;
		this.score = score;
		this.category = category;
		this.creationDate = creationDate;
		this.questions = questions;
		this.description = description;
		this.random = random;
		this.multiple_page = multiple_page;
		this.immediate_correction = immediate_correction;
	}
	
	

	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public boolean isRandom() {
		return random;
	}



	public void setRandom(boolean random) {
		this.random = random;
	}



	public boolean isMultiple_page() {
		return multiple_page;
	}



	public void setMultiple_page(boolean multiple_page) {
		this.multiple_page = multiple_page;
	}



	public boolean isImmediate_correction() {
		return immediate_correction;
	}



	public void setImmediate_correction(boolean immediate_correction) {
		this.immediate_correction = immediate_correction;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

}
