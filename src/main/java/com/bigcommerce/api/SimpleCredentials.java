package com.bigcommerce.api;

public class SimpleCredentials implements Credentials {

	private final String username;
	private final String apiKey;

	public SimpleCredentials(String username, String apiKey) {
		this.username = username;
		this.apiKey = apiKey;
	}

	public String getUsername() {
		return username;
	}

	public String getApiKey() {
		return apiKey;
	}

}
