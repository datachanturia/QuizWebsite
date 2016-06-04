package Database;

import java.sql.Connection;
import java.util.ArrayList;

import Model.Quiz;

public class QuizDaoImpl implements QuizDao {

	public QuizDaoImpl(Connection con) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Quiz> userCreatedQuizes(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Quiz> userTakenQuizes(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quiz getQuiz(int quizID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserCreatedQuiz(int userID, Quiz quiz) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUserTakenQuiz(int userID, int quizID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteQuiz(int quizID) {
		// TODO Auto-generated method stub

	}

}
