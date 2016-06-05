package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Answer;
import Model.Question;

public class QuestionDaoImpl implements QuestionDao {
	
	private Connection con;

	public QuestionDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Question> getQuizQuestions(int quizID) {
		ArrayList<Question> questions = new ArrayList<Question>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from questions where quizID = "+quizID);
			while(rs.next()){
				int questionID = rs.getInt("questionID");
				AnswerDaoImpl ans = new AnswerDaoImpl(con);
				Question q = new Question(questionID,rs.getString("question"),ans.getQuestionAnswers(questionID),rs.getInt("questiontype"));
				questions.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
	}

	@Override
	public void addQuestion(int quizID, Question question) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO questions (question,questiontype"
					+ "quizID) VALUES(?,?,?)");
			preparedStatement.setString(1, question.getQuestion());
			preparedStatement.setInt(2, question.getType().ordinal());
			preparedStatement.setInt(3, quizID);
			preparedStatement.execute();
			AnswerDaoImpl ans = new AnswerDaoImpl(con);
			ArrayList<Answer> answers = question.getAnswers();
			for (int i = 0; i < answers.size(); i++) {
				ans.addAnswer(question.getQuestionID(), answers.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteQuestion(int questionID) {
		
	}

}
