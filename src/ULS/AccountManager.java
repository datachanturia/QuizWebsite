package ULS;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.UserDaoImpl;
import Model.User;

public class AccountManager {
	
	private User currentUser;
	private Encrypt en;
	private UserDaoImpl udi;

	// constructor for account manager
	public AccountManager(Connection con) {
	}

	// factory class
	public void setConnection(java.sql.Connection con2) {
		this.en = new Encrypt();
		this.udi = new UserDaoImpl(con2);
	}

	// returns true if such account already exists
	public boolean accountExists(String email) {
		return udi.existsUser(email);
	}

	// return true if user name and password match account
	public boolean matchesAccount(String email, String password)
			throws ClassNotFoundException, SQLException, CloneNotSupportedException {

		if (!isValidMail(email)) {
			return false;
		}

		String passwordfinal = en.GenerationMode(password);
		User us = udi.getUser(email, passwordfinal);

		if (us == null) {
			return false;
		}
		currentUser = us;
		return true;
	}
	
	// return true if user name and password match account and they try to log in with soc network
	public boolean matchesSocAccount(String email, String password)
			throws ClassNotFoundException, SQLException, CloneNotSupportedException {

		if (!isValidMail(email)) {
			return false;
		}

		String passwordfinal = en.GenerationMode(password);
		User us = udi.getUser(email, passwordfinal);

		if (us == null) {
			return false;
		}
		currentUser = us;
		return true;
	}

	// creates new account
	public void createAccount(String userName, String email, String password) throws CloneNotSupportedException {

		String passwordfinal = en.GenerationMode(password);

		// when we don't have values we set -1 everywhere
		currentUser = new User(-1, userName, passwordfinal, email, "-1",
				new java.sql.Date(Calendar.getInstance().getTime().getTime()), false, false);
		udi.addUser(currentUser);
	}

	// creates new account only from facebook
	public void createSocAccount(String userName, String email, String password, String photo)
			throws CloneNotSupportedException {

		String passwordfinal = en.GenerationMode(password);

		// when we don't have values we set -1 everywhere
		currentUser = new User(-1, userName, passwordfinal, email, photo,
				new java.sql.Date(Calendar.getInstance().getTime().getTime()), false, true);
		udi.addUser(currentUser);
	}

	public boolean isValidMail(String email) {

		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Matcher mat = pattern.matcher(email);

		if (mat.matches()) {
			return true;
		}
		return false;
	}

	public User getUser() {
		return currentUser;
	}
}
