package Database;

import java.util.ArrayList;

import Model.Question;

public interface QuestionDao {

	public ArrayList<Question> getQuizQuestions(int quizID);
	public void addQuestion(int quizID, Question question);
	public void deleteQuestion(int questionID);
}
