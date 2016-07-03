package Database;

import java.sql.Date;
import java.util.ArrayList;

import Model.Quiz;
import Model.TakenQuiz;

public interface QuizDao {

	public ArrayList<Quiz> userCreatedQuizes(int userID);
	public ArrayList<Quiz> userTakenQuizes(int userID);
	public Quiz getQuiz(int quizID);
	public void addUserCreatedQuiz(int userID, Quiz quiz);
	public void addUserTakenQuiz(int userID, int quizID, Date taketime, int score);
	public void deleteQuiz(int quizID);
	public ArrayList<TakenQuiz> takenquiz(int quizID,int userID);
	public ArrayList<TakenQuiz> topPerformers(int quizID, boolean lastday,boolean recent);
	public ArrayList<Quiz> getPopularQuiz();
	public ArrayList<Quiz> getDayPopularQuiz();
	public ArrayList<Quiz> getNewQuiz();
	public ArrayList<Quiz> getQuizes(String prepStatement);
}
