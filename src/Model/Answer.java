package Model;

public class Answer {
	private int answerID;
	private String answer;
	private boolean isCorrect;
	
	public Answer(String answer, boolean isCorrect) {
		this.answer = answer;
		this.isCorrect = isCorrect;
	}
	
	

	public Answer(int answerID, String answer, boolean isCorrect) {
		this.answerID = answerID;
		this.answer = answer;
		this.isCorrect = isCorrect;
	}



	public int getAnswerID() {
		return answerID;
	}



	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}


	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String asnwer) {
		this.answer = asnwer;
	}
	
	public boolean getCorrect(){
		return this.isCorrect;
	}
	
	public void setCorrect(boolean isCorrect){
		this.isCorrect = isCorrect;
	}
}
