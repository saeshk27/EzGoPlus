package com.ezgo.web.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class EmailUtilsTest {

	private static final List<String> VALID_EMAIL_IDS = Arrays.asList("\"Fred Bloggs\"@example.com", "Chuck Norris <gmail@chucknorris.com>", "webmaster@müller.de", "matteo@78.47.122.114");
	private static final List<String> INVALID_EMAIL_IDS = Arrays.asList("user@.invalid.com", "@yahoo.com");

	@Test
	public void shouldPassValidEmailAddress() {
		VALID_EMAIL_IDS.forEach(emailId -> validateOutput(EmailUtils.isValidEmailAddress(emailId), true));
	}

	@Test
	public void shouldFailInValidEmailAddress() {
		INVALID_EMAIL_IDS.forEach(emailId -> validateOutput(EmailUtils.isValidEmailAddress(emailId), false));
	}

	public void validateOutput(boolean expectedResult, boolean actualResult) {
		assertEquals(expectedResult, actualResult);
	}
}
