package ULS;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.Connection;

import Database.UserDaoImpl;
import Model.User;

public class AccountManager {
	private User currentUser;

	Connection con;
	Encrypt en;
	UserDaoImpl udi;

	// constructor for account manager
	public AccountManager(Connection con) {
	}

	// factory class
	public void setConnection(Connection con) {
		this.en = new Encrypt();
		this.con = con;
		this.udi = new UserDaoImpl(con);
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

	// creates new account
	public void createAccount(String userName, String email, String password) throws CloneNotSupportedException {

		String passwordfinal = en.GenerationMode(password);

		// when we don't have values we set -1 everywhere
		currentUser = new User(-1, userName, passwordfinal, email, "-1",
				new java.sql.Date(Calendar.getInstance().getTime().getTime()), false);
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
