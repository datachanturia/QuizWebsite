package Model;

import java.util.ArrayList;

public class Question {
	
	private int questionID;
	private String question;
	private ArrayList<Answer> answers;
	private QuestionType type;
	
	public Question(int questionID, String question, ArrayList<Answer> answers,int type) {
		this.question = question;
		this.answers = answers;
		this.questionID = questionID;
		this.type = QuestionType.values()[type];
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	
	
}
