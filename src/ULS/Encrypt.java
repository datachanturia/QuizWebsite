package ULS;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Encrypt {
	// -------------------SPACE---FOR---VARIABLES---START-------------------//
	// Array of chars used to produce strings
	public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyz0123456789.,-!".toCharArray();

	private static Worker[] threads;
	private static CountDownLatch lt;
	private static CountDownLatch lt2;

	private static Integer numThreads;
	private static Integer strLen;
	private static String strCode;
	// --------------------SPACE---FOR---VARIABLES---END--------------------//

	/*
	 * Given a byte[] array, produces a hex String, such as "234a6f". with 2
	 * chars for each byte in the array. (provided code)
	 */
	public static String hexToString(byte[] bytes) {
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

	/*
	 * Given a string of hex byte values such as "24a26f", creates a byte[]
	 * array of those values, one byte value -128..127 for each 2 chars.
	 * (provided code)
	 */
	public static byte[] hexToArray(String hex) {
		byte[] result = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i += 2) {
			result[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
		}
		return result;
	}

	// "Generation Mode" is executed here..
	private static String GenerationMode(String pass) throws CloneNotSupportedException {
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

	private static void CrackingMode() throws CloneNotSupportedException {
		lt = new CountDownLatch(numThreads);
		lt2 = new CountDownLatch(numThreads);

		threads = new Worker[numThreads];
		for (int i = 0; i < numThreads; i++) {
			Worker a = new Worker();
			threads[i] = a;
			threads[i].start();
		}

	}

	private static ReentrantLock lock = new ReentrantLock();

	// worker class
	private static class Worker extends Thread {
		public void run() {
			int threadNum;
			lock.lock();
			lt.countDown();
			threadNum = (int) lt.getCount();
			lock.unlock();

			for (int i = 0; i < (CHARS.length) / numThreads; i++) {
				int index = threadNum * ((CHARS.length) / numThreads) + i;
				String firstChar = CHARS[index] + "";

				try {
					String str = GenerateString(firstChar, 1);
					String generatedCode = GenerationMode(firstChar);

					if (strCode.equals(generatedCode) || str.equals("(found)")) {
						//for (int j = 0; j < threads.length; j++){
						//	threads[j].interrupt();// this prevents threads from
						//							// extra work
						//}
						
						break;
					}

				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			lt2.countDown();
		}

		private String GenerateString(String firstChar, int len) throws CloneNotSupportedException {
			if (len == strLen) {
				return firstChar;
			}

			String generatedCodeOne = GenerationMode(firstChar);
			if (strCode.equals(generatedCodeOne)) {
				System.out.println(firstChar);
				return "(found)";
			}

			String gen = null;

			for (int i = 0; i < CHARS.length; i++) {
				gen = GenerateString(firstChar + CHARS[i], len + 1);
				if (gen.equals("(found)")) {
					return "(found)";
				}
				
				String generatedCode = GenerationMode(gen);
				if (strCode.equals(generatedCode)) {
					System.out.println(gen);

					return "(found)";
				}
			}
			return gen;
		}
	}

	// --------HERE --- GOES --- MAIN--------//
	public static void main(String args[]) throws CloneNotSupportedException, InterruptedException {
		// Generation Mode
		if (args.length < 2) {
			System.out.println(GenerationMode(args[0]));
		}
		// Cracking Mode
		else {
			strCode = args[0];
			strLen = Integer.parseInt(args[1]);
			numThreads = Integer.parseInt(args[2]);

			CrackingMode();

			lt.await();
			lt2.await();
			System.out.println("all done");
		}
	}

	// possible test values:
	// a 86f7e437faa5a7fce15d1ddcb9eaeaea377667b8
	// fm adeb6f2a18fe33af368d91b09587b68e3abcb9a7
	// a! 34800e15707fae815d7c90d49de44aca97e2d759
	// xyz 66b27417d37e024c46526c2f6d358a754fc552f3
}
