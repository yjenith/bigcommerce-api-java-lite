package com.bigcommerce.api;

/**
 * Provides access to the credentials required to access a Bigcommerce REST API.
 */
public interface Credentials {
	public String getUsername();
	public String getApiKey();
}
