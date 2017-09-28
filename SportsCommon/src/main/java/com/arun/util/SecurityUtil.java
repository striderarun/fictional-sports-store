package com.arun.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class SecurityUtil {

	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	/**
	 * Generate secret key using HMAC 
	 * @param data
	 * @param secretKey
	 * @return
	 */
	public static String calculateRFC2104HMACForString(final String data, final String secretKey) {
		String returnVal = null;
		if (data != null && secretKey != null) {
			final SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA1_ALGORITHM);
			Exception exception = null;
			try {
				final Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
				mac.init(signingKey);
				final byte[] rawHmac = mac.doFinal(data.getBytes());
				byte[] aReturnBytes;
				aReturnBytes = org.apache.commons.codec.binary.Base64.encodeBase64(rawHmac);
				returnVal = new String(aReturnBytes, "utf-8");
			} catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				exception = noSuchAlgorithmException;
			} catch (InvalidKeyException invalidKeyException) {
				exception = invalidKeyException;
			} catch (UnsupportedEncodingException unsupportedEncodingException) {
				exception = unsupportedEncodingException;
			}
		}
		return returnVal;
	}
}
