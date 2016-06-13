package Model;

import java.sql.Date;
import java.util.ArrayList;

public class User {

	private int userID;
	private String username;
	private String password;
	private String mail;
	private String photo;
	private Date createDate;
	private boolean isAdmin;
	private ArrayList<Quiz> quizesCreated;
	private ArrayList<Quiz> quizesTaken;
	private ArrayList<Message> messages;
	private ArrayList<Request> requests;
	private ArrayList<Challenge> challenges;
	private ArrayList<User> friends;

	private boolean isFbAcc;
	private boolean isGpAcc;

	public User(int userID, String username, String password, String mail, String photo, Date createDate,
			boolean isAdmin, boolean isFb, boolean isGp) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.photo = photo;
		this.createDate = createDate;
		this.isAdmin = isAdmin;
		this.isFbAcc = isFb;
		this.isGpAcc = isGp;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public boolean getFbBool() {
		return this.isFbAcc;
	}

	public void setFbBool(boolean isFbAcc) {
		this.isFbAcc = isFbAcc;
	}

	public boolean getGpBool() {
		return this.isGpAcc;
	}

	public void setGpBool(boolean isGpAcc) {
		this.isGpAcc = isFbAcc;
	}
}
