package Model;

public class Answer {
	private String answer;
	private boolean isCorrect;
	
	public Answer(String answer, boolean isCorrect) {
		this.answer = answer;
		this.isCorrect = isCorrect;
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
