package ULS;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

	/*
	 * Given a byte[] array, produces a hex String, such as "234a6f". with 2
	 * chars for each byte in the array. (provided code)
	 */
	private  hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff; // remove higher bits, sign
			if (val < 16)
				buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

	// "Generation Mode" is executed here..
	public String GenerationMode(String pass) throws CloneNotSupportedException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] bytPass = md.digest(pass.getBytes());

			return hexToString(bytPass);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failed";
	}

	// possible test values:
	// a 86f7e437faa5a7fce15d1ddcb9eaeaea377667b8
	// fm adeb6f2a18fe33af368d91b09587b68e3abcb9a7
	// a! 34800e15707fae815d7c90d49de44aca97e2d759
	// xyz 66b27417d37e024c46526c2f6d358a754fc552f3
}
