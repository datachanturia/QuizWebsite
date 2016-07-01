package Model;

import java.sql.Date;

public class Message {
	
	private int messageID;
	private int senderID;
	private int receiverID;
	private String message;
	private String senderName;
	private String recieverName;
	private Date sendDate;
	private boolean isread;
	
	public Message(int messageID, int senderID, int receiverID, String message, String senderName, String recieverName, Date sendDate,
			boolean isread) {
		super();
		this.messageID = messageID;
		this.senderID = senderID;
		this.receiverID = receiverID;
		this.message = message;
		this.senderName = senderName;
		this.recieverName = recieverName;
		this.sendDate = sendDate;
		this.isread = isread;
	}
	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	public int getSenderID(){
		return this.senderID;
	}
	public void setSenderID(int senderID){
		this.senderID = senderID;
	}
	public int getReceiverID(){
		return this.receiverID;
	}
	public void setReceiverID(int receiverID){
		this.receiverID = receiverID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getRecieverName() {
		return recieverName;
	}
	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public boolean isIsread() {
		return isread;
	}
	public void setIsread(boolean isread) {
		this.isread = isread;
	}
	
	
	
}
