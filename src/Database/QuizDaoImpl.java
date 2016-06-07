package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Quiz;

public class QuizDaoImpl implements QuizDao {

	private Connection con;

	public QuizDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Quiz> userCreatedQuizes(int userID) {
		ArrayList<Quiz> quizes = new ArrayList<Quiz>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select quizID from Quiz where authorID = " + userID + " and isdelete = 0");
			while (rs.next()) {
				Quiz qz = getQuiz(rs.getInt("quizID"));
				quizes.add(qz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizes;
	}

	@Override
	public ArrayList<Quiz> userTakenQuizes(int userID) {
		ArrayList<Quiz> quizes = new ArrayList<Quiz>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select quizID from TakenQuiz where userID = " + userID + " and isdelete = 0");
			while (rs.next()) {
				quizes.add(getQuiz(rs.getInt("userID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizes;
	}

	@Override
	public Quiz getQuiz(int quizID) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Quiz where quizID = " + quizID + " and isdelete = 0");
			while (rs.next()) {
				QuestionDaoImpl qdi = new QuestionDaoImpl(con);

				Quiz qz = new Quiz(rs.getInt("quizID"), rs.getString("quizname"), rs.getInt("authorID"),
						rs.getInt("score"), rs.getString("category"), rs.getDate("creationdate"),
						qdi.getQuizQuestions(rs.getInt("quizID")));
				return qz;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void addUserCreatedQuiz(int userID, Quiz quiz) {
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("update Quiz set authorID = ? " + "where quizID = " + quiz.getQuizID());
			preparedStatement.setInt(1, userID);

			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addUserTakenQuiz(int userID, int quizID, Date takedate, int score) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"INSERT INTO TakenQuiz (userID, quizID, takedate, score) VALUES(?,?,?,?)");

			preparedStatement.setInt(1, userID);
			preparedStatement.setInt(2, quizID);
			preparedStatement.setDate(3, takedate);
			preparedStatement.setInt(4, score);

			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
