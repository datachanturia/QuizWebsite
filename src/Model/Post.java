package Model;

import java.sql.Date;

public class Post {

	private int messageID;
	private int adminID;
	private String message;
	private String adminName;

	public Post(int messageID, int adminID, String adminName, String message) {
		super();

		this.messageID = messageID;
		this.adminID = adminID;
		this.message = message;
		this.adminName = adminName;
	}

	public int getMessageID() {
		return this.messageID;
	}

	public int getAdminID() {
		return this.adminID;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getPost() {
		return this.message;
	}
}
