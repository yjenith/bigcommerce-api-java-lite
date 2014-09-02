package com.bigcommerce.api;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONValue;

/**
 * Builds a JSON formatted String of the Form parameters.
 * 
 * @author Jenith Michael Raj. Y
 * 
 */
public class Form {

	private Map<String, Object> parameters;

	private Form(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public static Form create() {
		return create(null);
	}

	/**
	 * Factory method, creates an instance of a filter. Used to build URLs to
	 * collection endpoints.
	 */
	public static Form create(Map<String, Object> parameters) {
		return (parameters == null) ? new Form(new HashMap<String, Object>())
				: new Form(parameters);
	}

	public <T> void addProperty(String parameter, T value) {
		this.parameters.put(parameter, value);
	}

	/**
	 * Converts the Form parameters into a JSON String that can be applied as
	 * POST/PUT parameters.
	 * 
	 * @return string
	 */
	public String toJson() {
		String json = null;
		try {
			json = buildJson(this.parameters);
		} catch (Exception e) {
			// TODO Log
			e.printStackTrace();
		}
		return (json != null) ? json : "";
	}

	private String buildJson(Map<String, Object> data) {
		return JSONValue.toJSONString(data);
	}

}
