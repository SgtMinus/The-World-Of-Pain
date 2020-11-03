package gleb.database;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	public static String encryptPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD2");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger bigInteger = new BigInteger(1, messageDigest);
			String hashPassword = bigInteger.toString(16);
			while (hashPassword.length() < 32) {
				hashPassword = "0" + hashPassword;
			}
			return hashPassword;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
