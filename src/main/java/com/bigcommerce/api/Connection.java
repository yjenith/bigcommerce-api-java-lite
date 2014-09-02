package com.bigcommerce.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Connection {

	String username;
	String password;
	String storeUrl;

	private InputStream responseStream;
	private HttpURLConnection transport;

	public Connection(String storeUrl, String username, String apikey) {
		this.storeUrl = storeUrl;
		this.username = username;
		this.password = apikey;
	}

	/**
	 * Get encoded string representing HTTP Basic authorization credentials for
	 * the request.
	 */
	private String getBasicAuthHeader() {
		String token = this.username + ":" + this.password;
		byte[] encodedToken = Base64.encodeBase64(token.getBytes());
		return "Basic " + new String(encodedToken);
	}

	/**
	 * Create the HTTP connection transport to specified URL path.
	 */
	private HttpURLConnection createTransport(String path, String verb)
			throws java.io.IOException {
		URL url = new URL(this.storeUrl + path);
		HttpURLConnection transport = (HttpURLConnection) url.openConnection();
		transport.setRequestMethod(verb);
		transport
				.setRequestProperty("Authorization", this.getBasicAuthHeader());

		// print request
		System.out.print(verb);
		System.out.print("\t");
		System.out.println(url.toString());

		return transport;
	}

	/**
	 * Make an HTTP GET request to the given endpoint.
	 */
	public Connection get(String path) {
		if (transport != null) {
			transport.disconnect();
		}

		try {

			transport = this.createTransport(path, "GET");
			printHeaders();

			this.responseStream = transport.getInputStream();
			// printResponse();
		} catch (Exception e) {

			System.out.println(e.toString());

		}

		return this;
	}

	/**
	 * Close any existing HTTP connection.
	 */
	private void closeExistingConnection() {
		if (transport != null) {
			transport.disconnect();
			transport = null;
		}
	}

	/**
	 * Close any open connection.
	 */
	public void close() {
		closeExistingConnection();
	}

	/**
	 * Make an HTTP POST request to the given endpoint.
	 */
	public boolean post(String path, String data) {

		closeExistingConnection();

		try {
			transport = this.createTransport(path, "POST");
			transport.setDoOutput(true);
			transport.setRequestProperty("Content-Type", "application/json");

			OutputStreamWriter post = new OutputStreamWriter(
					transport.getOutputStream());
			post.write(data);
			post.flush();
			post.close();

			printHeaders();
			printBody(data);

			this.responseStream = transport.getInputStream();
			printResponse();

			int result = transport.getResponseCode();
			if (result == HttpURLConnection.HTTP_CREATED) {
				return true;
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return false;
	}

	/**
	 * Make an HTTP PUT request to the given endpoint.
	 */
	public boolean put(String path, String data) {
		closeExistingConnection();

		try {
			transport = this.createTransport(path, "PUT");
			transport.setDoOutput(true);
			transport.setRequestProperty("Content-Type", "application/json");

			OutputStreamWriter put = new OutputStreamWriter(
					transport.getOutputStream());
			put.write(data);
			put.flush();
			put.close();

			printHeaders();
			printBody(data);

			this.responseStream = transport.getInputStream();
			printResponse();

			int result = transport.getResponseCode();
			if (result == HttpURLConnection.HTTP_OK) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return false;
	}

	/**
	 * Make an HTTP DELETE request to the given endpoint.
	 */
	public boolean delete(String path) {
		closeExistingConnection();

		try {
			transport = this.createTransport(path, "DELETE");
			printHeaders();
			printResponse();
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return false;
	}

	/**
	 * String representation of the raw HTTP response body.
	 */
	public String asString() {
		String responseBody = "";

		try {
			StringBuffer body = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					this.responseStream, "UTF-8"));
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				body.append(inputLine);
			}
			reader.close();
			responseBody = body.toString();

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			closeExistingConnection();
		}

		return responseBody;
	}

	/**
	 * XML representation of the HTTP response.
	 */
	public Element asXml() {
		Element responseXml = null;

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(this.responseStream);
			responseXml = document.getDocumentElement();

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			closeExistingConnection();
		}

		return responseXml;
	}

	
	public void printHeaders() {
		System.out.println("HEADERS");
		System.out.println("=======");
		for (Entry<String, List<String>> header : this.transport
				.getHeaderFields().entrySet()) {
			System.out.println(header.getKey() + "=" + header.getValue());
		}
	}

	public void printBody(String data) {
		System.out.println("BODY");
		System.out.println("====");
		System.out.println(data);

	}

	public void printResponse() {
		System.out.println("RESPONSE");
		System.out.println("========");
		//System.out.println(this.asString());
	}

}
