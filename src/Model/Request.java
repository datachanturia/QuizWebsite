package Model;

import java.sql.Date;

public class Request {
	
	private int requestID;
	private int senderID;
	private int recieverID;
	private Date sendDate;
	private boolean isread;
	
	public Request(int requestID, int senderID, int recieverID, Date sendDate, boolean isread) {
		this.requestID = requestID;
		this.senderID = senderID;
		this.recieverID = recieverID;
		this.sendDate = sendDate;
		this.isread = isread;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getSenderID() {
		return senderID;
	}

	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}

	public int getRecieverID() {
		return recieverID;
	}

	public void setRecieverID(int recieverID) {
		this.recieverID = recieverID;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public boolean isread() {
		return isread;
	}

	public void setIsread(boolean isread) {
		this.isread = isread;
	}
	
	
	
}