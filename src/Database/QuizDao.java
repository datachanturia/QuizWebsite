package Database;

import java.util.ArrayList;

import Model.Quiz;

public interface QuizDao {

	public ArrayList<Quiz> userCreatedQuizes(int userID);
	public ArrayList<Quiz> userTakenQuizes(int userID);
	public Quiz getQuiz(int quizID);
	public void addUserCreatedQuiz(int userID, Quiz quiz);
	public void addUserTakenQuiz(int userID, int quizID);
	public void deleteQuiz(int quizID);
}
