package ULS;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import Database.MyDBInfo;
import Database.UserDaoImpl;
import Model.User;

public class AccountManager {
	private static Vector<Account> accounts;

	// constructor for account manager
	public AccountManager() {
		accounts = new Vector<Account>();
		Account a = new Account("Patrick", "1234");
		Account b = new Account("Molly", "FloPup");
		accounts.add(a);
		accounts.add(b);
	}

	// returns true if such account already exists
	public boolean accountExists(String userName) {
		if (userName.equals(""))
			return true;
		for (int i = 0; i < accounts.size(); i++) {
			if (userName.toLowerCase().equals(accounts.get(i).getName().toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	// return true if user name and password match account
	public boolean matchesAccount(String email, String password)
			throws ClassNotFoundException, SQLException, CloneNotSupportedException {
		if (email == "" || email == null || password == "" || password == null) {
			return false;
		}
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER,
				MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);

		UserDaoImpl udi = new UserDaoImpl(con);
		Encrypt en = new Encrypt();
		String passwordfinal = en.GenerationMode(password);
		User us = udi.getUser(email, passwordfinal);

		con.close();
		if (us == null) {
			return false;
		}
		return true;
	}

	// creates new account
	public void createAccount(String userName, String password) {
		if (!userName.equals("")) {
			Account a = new Account(userName, password);
			accounts.add(a);
		}
	}

	// in this class we just store information conveniently
	private class Account {
		private String name;
		private String pass;

		// constructor to set variables
		public Account(String userName, String password) {
			name = userName;
			pass = password;
		}

		// here you can get account name
		public String getName() {
			return name;
		}

		// only acc class knows if two acc class objects are equal
		public boolean equalsAcc(String nm, String ps) {
			if (ps.equals(pass) && nm.equals(name)) {
				return true;
			}
			return false;
		}
	}

}
