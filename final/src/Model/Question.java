package Model;

import java.util.ArrayList;

public class Question {
	
	private String question;
	private ArrayList<Answer> answers;
	private QuestionType type;
	
	public Question(String question, ArrayList<Answer> answers) {
		this.question = question;
		this.answers = answers;
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
	
	
}
