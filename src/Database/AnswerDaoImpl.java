package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Answer;
import Model.Question;

public class AnswerDaoImpl implements AnswerDao {

	private Connection con;
	
	public AnswerDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Answer> getQuestionAnswers(int questionID) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Answers where questionID = " + questionID
					+ "and isdelete = 0");
			while(rs.next()){
				Answer ans = new Answer(rs.getString("answer"), rs.getBoolean("iscorrect"));
				answers.add(ans);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return answers;
	}

	@Override
	public void addAnswer(int questionID, Answer answer) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Answers (answer, iscorrect,questionID) VALUES(?,?,?)");
			preparedStatement.setString(1, answer.getAnswer());
			preparedStatement.setBoolean(2, answer.getCorrect());
			preparedStatement.setInt(3, questionID);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAnswer(int answerID) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement("update Answers set isdelete = ? "
					+ "where answerID = " + answerID);
			preparedStatement.setInt(1, 1);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
