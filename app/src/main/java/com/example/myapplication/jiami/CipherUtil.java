package com.example.myapplication.jiami;

import java.security.Key;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;

public class CipherUtil {
	public CipherUtil() {
	}

	public static byte[] encrypt(Key key, byte[] src, String algorithm) {
		try {
			Cipher e = Cipher.getInstance(algorithm);
			e.init(1, key);
			return e.doFinal(src);
		} catch (Exception var4) {
			throw new RuntimeException(var4);
		}
	}

	public static byte[] decrypt(Key key, byte[] src, String algorithm) {
		try {
			Cipher e = Cipher.getInstance(algorithm);
			e.init(2, key);
			return e.doFinal(src);
		} catch (Exception var4) {
			throw new RuntimeException(var4);
		}
	}

	public static byte[] decrypt(Key key, byte[] src, String algorithm, AlgorithmParameterSpec algorithmParameterSpec)
			throws Exception {
		try {
			Cipher e = Cipher.getInstance(algorithm);
			e.init(2, key, algorithmParameterSpec);
			return e.doFinal(src);
		} catch (Exception var5) {
			throw new RuntimeException(var5);
		}
	}

	public static byte[] mapping(byte[] dict, byte[] src) {
		return mapping(dict, src, true);
	}

	public static byte[] mapping(byte[] dict, byte[] src, boolean inPlace) {
		if (dict.length != 256) {
			throw new IllegalArgumentException("The length of dict should be 256");
		} else {
			byte[] result = inPlace ? src : new byte[src.length];

			for (int i = 0; i < src.length; ++i) {
				result[i] = dict[src[i] + 128];
			}

			return result;
		}
	}

	public static String sha1(String input) {
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			byte[] result = mDigest.digest(input.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (Exception e) {
		}
		return null;
	}
}
