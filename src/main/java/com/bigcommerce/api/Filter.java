package com.bigcommerce.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.bigcommerce.util.QueryStringBuilder;

/**
 * Builds a query to filter the results of a collection request.
 * 
 * @author jyogapa1
 * 
 */
public class Filter {

	private Map<String, String> parameters;

	private Filter(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public static Filter create() {
		return create(null);
	}

	/**
	* Factory method, creates an instance of a filter.
	* Used to build URLs to collection endpoints.
	*/
	public static Filter create(Map<String, String> parameters) {
		return (parameters == null) ? new Filter(new HashMap<String, String>())
				: new Filter(parameters);
	}

	public void add(String parameter, String value) {
		this.parameters.put(parameter, value);
	}

	/**
	 * Converts the filter into a URL querystring that can be applied as GET
	 * parameters.
	 * 
	 * @return string
	 */
	public String toQuery() {
		String query = null;
		try {
			query = httpBuildQuery(this.parameters);
		} catch (UnsupportedEncodingException e) {
			// TODO Log
			e.printStackTrace();
		}
		return (query != null) ? query : "";
	}

	private String httpBuildQuery(Map<String, String> data)
			throws UnsupportedEncodingException {
		QueryStringBuilder builder = new QueryStringBuilder();
		for (Entry<String, String> pair : data.entrySet()) {
			builder.addQueryParameter(pair.getKey(), pair.getValue());
		}
		return builder.encode("UTF-8");
	}

}
