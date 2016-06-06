package Model;

import java.sql.Date;
import java.util.ArrayList;

public class User {
	
	private int userID;
	private String username;
	private String password;
	private Date createDate;
	private boolean isAdmin;
	private ArrayList<Quiz> quizesCreated;
	private ArrayList<Quiz> quizesTaken;
	private ArrayList<Message> messages;
	private ArrayList<Request> requests;
	private ArrayList<Challenge> challenges;
	private ArrayList<User> friends;
	
	public User(int userID, String username, String password, Date createDate, boolean isAdmin) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.createDate = createDate;
		this.isAdmin = isAdmin;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public ArrayList<Quiz> getQuizesCreated() {
		return quizesCreated;
	}

	public void setQuizesCreated(ArrayList<Quiz> quizesCreated) {
		this.quizesCreated = quizesCreated;
	}

	public ArrayList<Quiz> getQuizesTaken() {
		return quizesTaken;
	}

	public void setQuizesTaken(ArrayList<Quiz> quizesTaken) {
		this.quizesTaken = quizesTaken;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	public ArrayList<Request> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<Request> requests) {
		this.requests = requests;
	}

	public ArrayList<Challenge> getChallenges() {
		return challenges;
	}

	public void setChallenges(ArrayList<Challenge> challenges) {
		this.challenges = challenges;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}
}
