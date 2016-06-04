package Database;

import java.util.ArrayList;

import Model.Answer;

public interface AnswerDao {
	
	public ArrayList<Answer> getQuestionAnswers(int questionID);
	public void addAnswer(int questionID, Answer answer);
	public void deleteAnswer(int answerID);
	
}
