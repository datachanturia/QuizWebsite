package Model;

public class Answer {
	private String asnwer;
	private boolean isCorrect;
	
	public Answer(String answer, boolean isCorrect) {
		this.asnwer = answer;
		this.isCorrect = isCorrect;
	}

	public String getAsnwer() {
		return this.asnwer;
	}

	public void setAsnwer(String asnwer) {
		this.asnwer = asnwer;
	}
	
	public boolean getCorrect(){
		return this.isCorrect;
	}
	
	public void setCorrect(boolean isCorrect){
		this.isCorrect = isCorrect;
	}
}
