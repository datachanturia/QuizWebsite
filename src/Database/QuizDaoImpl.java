package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Quiz;

public class QuizDaoImpl implements QuizDao {
	
	private Connection con;

	public QuizDaoImpl(Connection con) {
		this.con = con;
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
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("update Quiz set isdelete = ? " + "where quizID = " + quizID);
			preparedStatement.setInt(1, 1);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
