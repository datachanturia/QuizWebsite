package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import Model.Message;
import Model.Post;

public class PostDaoImpl implements PostDao {
	private Connection con;

	public PostDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Post> getPosts() {
		ArrayList<Post> posts = new ArrayList<Post>();

		try {
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery("select * from AdminPosts order by messageID desc limit 10");

			while (result.next()) {
				Post post = new Post(result.getInt("messageID"), result.getInt("adminID"),
						result.getString("adminName"), result.getString("message"));
				posts.add(post);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public void addPost(String message, int adminID, String adminName) {
		try {
			PreparedStatement prepst = con.prepareStatement(
					"insert into AdminPosts (adminID, adminName, message, senddate) " + " values (?,?,?,?)");

			prepst.setInt(1, adminID);
			prepst.setString(2, adminName);
			prepst.setString(3, message);
			prepst.setDate(4, (new java.sql.Date(Calendar.getInstance().getTime().getTime())));
			prepst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
