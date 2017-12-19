package com.ezgo.web.security;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordUtilsTest {

	private static final String PLAIN_PASSWORD = "password";
	private static final String HASHED_PASSWORD = "5f4dcc3b5aa765d61d8327deb882cf99";
	private static final String INVALID_HASHED_PASSWORD = "pwd";

	PasswordUtils passwordUtils = new PasswordUtils();

	@Test
	public void shouldGetHashedPassword() {
		String algorithmPassword = passwordUtils.getHashedPassword(PLAIN_PASSWORD);
		assertEquals(algorithmPassword, HASHED_PASSWORD);
	}

	@Test
	public void shouldNotGetHashedPasswordForNull() {
		String algorithmPassword = passwordUtils.getHashedPassword(null);
		assertNull(algorithmPassword);
	}

	@Test
	public void shouldPassValidPassword() {
		assertTrue(passwordUtils.isValidPassword(PLAIN_PASSWORD, HASHED_PASSWORD));
	}

	@Test
	public void shouldFailValidPassword() {
		assertFalse(passwordUtils.isValidPassword(PLAIN_PASSWORD, INVALID_HASHED_PASSWORD));
	}

	@Test
	public void shouldGetDecryptedPassword() {
		String algorithmPassword = passwordUtils.getDecryptedPassword(HASHED_PASSWORD);
		assertEquals(algorithmPassword, HASHED_PASSWORD);
	}

	@Test
	public void shouldNotGetDecryptedPasswordForNull() {
		String algorithmPassword = passwordUtils.getDecryptedPassword(null);
		assertNull(algorithmPassword);
	}

}
