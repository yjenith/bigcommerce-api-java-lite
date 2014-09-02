package com.bigcommerce.api;

import com.bigcommerce.api.resources.BaseResource;

/**
 * Facade for accessing a Bigcommerce store via the REST API.
 * 
 * @author Jenith
 */
public class Store {
	private Connection connection;

	/**
	 * A constructor that accepts any object implementing
	 * {@link com.bigcommerce.api.auth.Credentials Credentials}.
	 * 
	 * @param storeUrl
	 * @param credentials
	 */
	public Store(String storeUrl, Credentials credentials) {
		this.connection = new Connection(storeUrl, credentials.getUsername(),
				credentials.getApiKey());
	}

	/**
	 * A simple constructor that accepts a username and API key as credentials.
	 * 
	 * @param storeUrl
	 * @param username
	 * @param apiKey
	 */
	public Store(String storeUrl, String username, String apiKey) {
		this(storeUrl, new SimpleCredentials(username, apiKey));
	}

	/**
	 * Gets new generic resource to manage.
	 * 
	 * @param <T>
	 * 
	 * @return
	 */
	public <T extends Response> BaseResource<T> newResource(Class<T> className,
			String path, String rootItemTag) {

		BaseResource<T> br = new BaseResource<T>(className, this.connection,
				path.toString(), rootItemTag);

		return br;
	}

	/**
	 * Closes api connection
	 */
	public void close() {
		this.connection.close();
	}

}
