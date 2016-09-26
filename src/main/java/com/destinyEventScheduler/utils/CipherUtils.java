package com.destinyEventScheduler.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CipherUtils {

	@Value("${encrypt_key}")
	private String encryptKey;

	private Cipher cipher;
	private SecretKey aesKey;

	@PostConstruct
	public void init() {
		try {
			byte[] key = (encryptKey).getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);

			aesKey = new SecretKeySpec(key, "AES");
			cipher = Cipher.getInstance("AES");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("CipherUtils init", e);
		}
	}

	public String encrypt(String value) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] text = value.getBytes();
		byte[] textEncrypted = cipher.doFinal(text);
		return bytesToString(textEncrypted);
	}

	public String decrypt(String value) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		byte[] textDecrypted = cipher.doFinal(stringToBytes(value));
		return new String(textDecrypted, StandardCharsets.ISO_8859_1);
	}

	private String bytesToString(byte[] b) {
	    byte[] b2 = new byte[b.length + 1];
	    b2[0] = 1;
	    System.arraycopy(b, 0, b2, 1, b.length);
	    return new BigInteger(b2).toString(36);
	}

	private byte[] stringToBytes(String s) {
	    byte[] b2 = new BigInteger(s, 36).toByteArray();
	    return Arrays.copyOfRange(b2, 1, b2.length);
	}
	
}