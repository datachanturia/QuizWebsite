package Database;

import java.sql.Connection;
import java.util.ArrayList;

import Model.Answer;

public class AnswerDaoImpl implements AnswerDao {

	public AnswerDaoImpl(Connection con) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Answer> getQuestionAnswers(int questionID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAnswer(int questionID, Answer answer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAnswer(int answerID) {
		// TODO Auto-generated method stub

	}

}
