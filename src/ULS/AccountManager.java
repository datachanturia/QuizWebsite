package ULS;

import java.util.Vector;

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
		if(userName.equals("")) return true;
		for (int i = 0; i < accounts.size(); i++) {
			if (userName.toLowerCase().equals(accounts.get(i).getName().toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	// return true if user name and password match account
	public boolean matchesAccount(String userName, String password) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).equalsAcc(userName, password)) {
				return true;
			}
		}
		return false;
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
