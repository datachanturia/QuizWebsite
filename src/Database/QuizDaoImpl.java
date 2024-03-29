package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Question;
import Model.Quiz;
import Model.TakenQuiz;
import dataSrc.MyDBInfo;

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
					.executeQuery("select quizID from Quiz where authorID=" + userID + " and isdelete=0");
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
				quizes.add(getQuiz(rs.getInt("quizID")));
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
			ResultSet rs = stmt.executeQuery("select * from quiz where quizID=" + quizID);
			while (rs.next()) {
				QuestionDaoImpl qdi = new QuestionDaoImpl(con);

				Quiz qz = new Quiz(rs.getInt("quizID"), rs.getString("quizname"), rs.getInt("authorID"),
						rs.getInt("score"), rs.getString("category"), rs.getDate("creationdate"),
						qdi.getQuizQuestions(rs.getInt("quizID")),rs.getString("description"),
						rs.getBoolean("random"),rs.getBoolean("multiple_page"),rs.getBoolean("immediate_correction"));
				return qz;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void addUserCreatedQuiz(int userID, Quiz quiz) {
		int qid = -1;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"INSERT INTO quiz (quizname,authorID," + "score,category,creationdate,description,random,multiple_page,immediate_correction) VALUES(?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, quiz.getQuizname());
			preparedStatement.setInt(2, userID);
			preparedStatement.setInt(3, quiz.getScore());
			preparedStatement.setString(4, quiz.getCategory());
			preparedStatement.setDate(5, quiz.getCreationDate());
			preparedStatement.setString(6, quiz.getDescription());
			preparedStatement.setBoolean(7, quiz.isRandom());
			preparedStatement.setBoolean(8, quiz.isMultiple_page());
			preparedStatement.setBoolean(9, quiz.isImmediate_correction());

			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				qid = (int) rs.getLong(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		QuestionDaoImpl qdao = new QuestionDaoImpl(con);
		ArrayList<Question> questions = quiz.getQuestions();
		for (int i = 0; i < questions.size(); i++) {
			qdao.addQuestion(qid, questions.get(i));
		}

	}

	@Override
	public void addUserTakenQuiz(int userID, int quizID, Date takedate, int score,long time) {
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("INSERT INTO TakenQuiz (userID, quizID, takedate, score,quiztime) VALUES(?,?,?,?,?)");

			preparedStatement.setInt(1, userID);
			preparedStatement.setInt(2, quizID);
			preparedStatement.setDate(3, takedate);
			preparedStatement.setInt(4, score);
			preparedStatement.setLong(5, time);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteQuiz(int quizID) {
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("update Quiz set isdelete = 1 where quizID = " + quizID);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// --------------------------------------------------------------------------------------------------
	@Override
	public ArrayList<Quiz> getPopularQuiz() {
		ArrayList<Quiz> ls = new ArrayList<Quiz>();
		try {
			Statement stmt = con.createStatement();

			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

			PreparedStatement prdtmt = con
					.prepareStatement("SELECT quizID FROM takenquiz where isdelete = 0 GROUP BY quizID desc limit 6");

			ResultSet rs = prdtmt.executeQuery();
			while (rs.next()) {
				Quiz qzz = getQuiz(rs.getInt("quizID"));
				ls.add(qzz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public ArrayList<Quiz> getDayPopularQuiz() {
		ArrayList<Quiz> ls = new ArrayList<Quiz>();
		try {
			Statement stmt = con.createStatement();

			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

			PreparedStatement prdtmt = con.prepareStatement(
					"SELECT quizID FROM takenquiz WHERE quizID IN (SELECT quizID FROM Quiz WHERE creationdate > SUBDATE(NOW(),1) and isdelete=0) GROUP BY score desc limit 6");
			
			ResultSet rs = prdtmt.executeQuery();
			
			
			while (rs.next()) {
				Quiz qzz = getQuiz(rs.getInt("quizID"));
				ls.add(qzz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}

	@Override
	public ArrayList<Quiz> getNewQuiz() {
		ArrayList<Quiz> ls = new ArrayList<Quiz>();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

			PreparedStatement prdtmt = con
					.prepareStatement("select quizID from quiz where isdelete = 0 order by quizID desc limit 6");

			ResultSet rs = prdtmt.executeQuery();
			while (rs.next()) {
				Quiz qzz = getQuiz(rs.getInt("quizID"));
				ls.add(qzz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public ArrayList<TakenQuiz> takenquiz(int quizID, int userID) {
		ArrayList<TakenQuiz> tq = new ArrayList<TakenQuiz>();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

			ResultSet rs = stmt.executeQuery("select * from TakenQuiz where userID="+userID+" and quizID="+quizID);
		
			while (rs.next()) {
				tq.add(new TakenQuiz(rs.getDate("takedate"), rs.getInt("score"),rs.getLong("quiztime")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tq;
	}
	
	public TakenQuiz userMaxInQuiz(int quizID, int userID) {
		TakenQuiz tq = null;
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

			ResultSet rs = stmt.executeQuery("select * from TakenQuiz where userID="+userID+" and quizID="+quizID+" order by score desc limit 1");
		
			while (rs.next()) {
				tq=new TakenQuiz(rs.getDate("takedate"), rs.getInt("score"),rs.getLong("quiztime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tq;
	}

	@Override
	public ArrayList<TakenQuiz> topPerformers(int quizID, boolean lastday, boolean recent) {
		ArrayList<TakenQuiz> tq = new ArrayList<TakenQuiz>();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
			String query = "";
			if(lastday)
				query = "select * from TakenQuiz where quizID="+quizID+" and date_sub(curdate(),interval 1 day) order by score desc limit 6";
			else if(recent)
				query = "select * from TakenQuiz where quizID="+quizID+" order by takedate desc limit 6";
			else
				query = "select * from TakenQuiz where quizID="+quizID+" order by score desc limit 6";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				tq.add(new TakenQuiz(rs.getInt("userID"),rs.getInt("quizID"),rs.getDate("takedate"), rs.getInt("score"),rs.getLong("quiztime")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tq;
	}
	
	public ArrayList<Quiz> getQuizes(String prepStatement){
		ArrayList<Quiz> ls = new ArrayList<Quiz>();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

			PreparedStatement prdtmt = con
					.prepareStatement(prepStatement);

			ResultSet rs = prdtmt.executeQuery();
			while (rs.next()) {
				Quiz qzz = getQuiz(rs.getInt("quizID"));
				ls.add(qzz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	};

}
