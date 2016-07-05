package Database;

import java.util.ArrayList;

import Model.Message;
import Model.Post;

public interface PostDao {

	public ArrayList<Post> getPosts();

	public void addPost(String message, int adminID, String adminName);
}
