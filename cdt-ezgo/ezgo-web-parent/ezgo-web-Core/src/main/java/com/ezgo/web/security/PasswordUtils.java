package com.ezgo.web.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordUtils.class);

	@Autowired
	PBKDF2CryptAlgorithmUtils hashAlgorithmUtils;

	public PBKDF2CryptAlgorithmUtils getHashAlgorithmUtils() {
		return hashAlgorithmUtils;
	}

	public void setHashAlgorithmUtils(PBKDF2CryptAlgorithmUtils hashAlgorithmUtils) {
		this.hashAlgorithmUtils = hashAlgorithmUtils;
	}

	public String getHashedPassword(String plainPassword) {
		return getMD5HashedPassword(plainPassword);
	}

	public boolean isValidPassword(String plainPassword, String hashedPassword) {
		return StringUtils.equals(getMD5HashedPassword(plainPassword), hashedPassword);
	}

	public String getDecryptedPassword(String hashedPassword) {
		String plainPassword = hashedPassword;
		return plainPassword;
	}

	private String getMD5HashedPassword(String plainPassword) {
		if (StringUtils.isEmpty(plainPassword)) {
			return plainPassword;
		}
		try {
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			byte[] array = mDigest.digest(plainPassword.getBytes());
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < array.length; ++i) {
				builder.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return builder.toString();
		} catch (NoSuchAlgorithmException ex) {
			LOGGER.error("getMD5HashedPassword() | Throws exception for input: " + plainPassword, ex);
		}
		return null;
	}

}
