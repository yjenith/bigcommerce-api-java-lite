package com.bigcommerce.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Builds a query to filter the results of a collection request.
 * 
 * @author Jenith Michael Raj. Y
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
	 * Factory method, creates an instance of a filter. Used to build URLs to
	 * collection endpoints.
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
		query = httpBuildQuery(this.parameters);
		return (query != null) ? query : "";
	}

	/**
	 * Build a query string.
	 * 
	 * @param params
	 * @return
	 */
	protected String httpBuildQuery(Map<String, String> params) {
		if (params == null || params.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder("?");
		int i = 0;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (i > 0) {
				sb.append("&");
			}
			try {
				sb.append(key)
						.append("=")
						.append(URLEncoder.encode(value, Charset
								.defaultCharset().name()));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			i++;
		}
		return sb.toString();
	}

}
