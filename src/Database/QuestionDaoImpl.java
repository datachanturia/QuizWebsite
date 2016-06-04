package Database;

import java.sql.Connection;
import java.util.ArrayList;

import Model.Question;

public class QuestionDaoImpl implements QuestionDao {

	public QuestionDaoImpl(Connection con) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Question> getQuizQuestions(int quizID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addQuestion(int quizID, Question question) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteQuestion(int questionID) {
		// TODO Auto-generated method stub

	}

}
