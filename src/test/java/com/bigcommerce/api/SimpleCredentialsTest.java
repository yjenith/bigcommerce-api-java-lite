package com.bigcommerce.api;

import static org.junit.Assert.*;
import org.junit.Test;


public class SimpleCredentialsTest {

	@Test
	public void testGetUsernameEqualsProvidedUsername() {
		String expectedUsername = "username";
		SimpleCredentials credentials = new SimpleCredentials(expectedUsername, "apiKey");
		assertEquals(expectedUsername, credentials.getUsername());
	}
	
	@Test
	public void testGetApiKeyEqualsProvidedApiKey() {
		String expectedApiKey = "25c1238e890bfad9b2c4b387887dea02ea6210d1";
		SimpleCredentials credentials = new SimpleCredentials("username", expectedApiKey);
		assertEquals(expectedApiKey, credentials.getApiKey());
	}
}
