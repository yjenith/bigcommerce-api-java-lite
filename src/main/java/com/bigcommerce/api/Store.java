package com.bigcommerce.api;

import com.bigcommerce.api.resources.Orders;
import com.bigcommerce.api.resources.Products;

/**
 * Facade for accessing a Bigcommerce store via the REST API.
 */
public class Store {
	private Connection connection;

	/**
	 * A constructor that accepts any object implementing
	 * {@link com.bigcommerce.api.auth.Credentials Credentials}.
	 * @param storeUrl
	 * @param credentials
	 */
	public Store(String storeUrl, Credentials credentials) {
		this.connection = new Connection(storeUrl, credentials.getUsername(), credentials.getApiKey());
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
	 * Gets Orders resource to manage.
	 * 
	 * 
	 * @return
	 */
	public Orders getOrders(){
		return new Orders(this.connection);
	}
	
	/**
	 *  Gets Products resource to manage.
	 * 
	 * @return
	 */
	public Products getProducts(){
		return new Products(this.connection);
	}

}
