package com.bigcommerce.api;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

//import sun.misc.BASE64Encoder;
//import javax.mail.internet.MimeUtility;

import com.bigcommerce.utils.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Connection
{

    String username;
    String password;
    String storeUrl;
    String basePrefix;

    public Connection(String storeurl, String username, String apikey)
    {
        this.storeUrl = storeurl;
        this.username = username;
        this.password = apikey;
    }

    /**
     * Get encoded string representing HTTP Basic authorization credentials for the request.
     */
    private String getBasicAuthHeader()
    {
        String token = this.username + ":" + this.password;
        //BASE64Encoder base64 = new sun.misc.BASE64Encoder();
        //String encodedToken = base64.encode(token.getBytes());

        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //OutputStream encodedToken = MimeUtility.encode(baos, "base64");
        //encodedToken.write(b);
        //encodedToken.close();

        String encodedToken = Base64.encodeString(token);

        return "Basic " + encodedToken;
    }

    /**
     * Create the HTTP connection transport to specified URL path.
     */
    private HttpURLConnection createTransport(String path, String verb) throws java.io.IOException
    {
        URL url = new URL(this.storeUrl + this.basePrefix + path);
        HttpURLConnection transport = (HttpURLConnection) url.openConnection();
        transport.setRequestMethod(verb);
        transport.setRequestProperty("Authorization", this.getBasicAuthHeader());
        //transport.setRequestProperty("Content-type", "application/xml");
        //transport.setRequestProperty("Accept", "application/xml");
        return transport;
    }

    private InputStream responseStream;

    /**
     * Make an HTTP GET request to the given endpoint.
     */
    public Connection get(String path)
    {
    	HttpURLConnection transport = null;

		try {

			transport = this.createTransport(path, "GET");
    		this.responseStream = transport.getInputStream();

		} catch(Exception e) {

			System.out.print(e.toString());

		} finally {

			if (transport != null) {
	            transport.disconnect();
	        }

		}

		return this;
    }

    /**
     * Make an HTTP POST request to the given endpoint.
     */
    public boolean post(String path, String data)
    {
        HttpURLConnection transport = null;

        try {

            transport = this.createTransport(path, "POST");

	        transport.setDoOutput(true);
            OutputStreamWriter post = new OutputStreamWriter(transport.getOutputStream());
            post.write(data);
            post.flush();
            post.close();

            this.responseStream = transport.getInputStream();

        } catch(Exception e) {
            System.out.print(e.toString());

        } finally {

            if (transport != null) {
	            transport.disconnect();
	        }

        }

        return false;
    }

    /**
     * Make an HTTP PUT request to the given endpoint.
     */
    public boolean put(String path, String data)
    {
        HttpURLConnection transport = null;

        try {

            transport = this.createTransport(path, "PUT");

	        this.responseStream = transport.getInputStream();

        } catch(Exception e) {
            System.out.print(e.toString());

        } finally {

            if (transport != null) {
	            transport.disconnect();
	        }

        }

        return false;
    }

    /**
     * Make an HTTP DELETE request to the given endpoint.
     */
    public boolean delete(String path)
    {
        HttpURLConnection transport = null;

        try {

            transport = this.createTransport(path, "DELETE");

	        //String responseHeader = connection.getHeaderField(0);
            //String[] headerArray = responseHeader.split(" ");
            //int responseCode = Integer.parseInt(headerArray[1]);
            //if (responseCode == 201) {

        } catch(Exception e) {
            System.out.print(e.toString());

        } finally {

         	if (transport != null) {
	            transport.disconnect();
	        }

        }

        return false;
    }

    /**
     * String representation of the raw HTTP response body.
     */
    public String asString()
    {
    	String responseBody = "";

    	try {
            StringBuffer body = new StringBuffer();
    		BufferedReader reader = new BufferedReader(new InputStreamReader(this.responseStream, "UTF-8"));
    		String inputLine;
    		while ((inputLine = reader.readLine()) != null) {
    			body.append(inputLine);
    		}
    		reader.close();
    		responseBody = body.toString();

    	} catch (Exception e) {
    		System.out.print(e.toString());
    	}

		return responseBody;
    }

    /**
     * XML representation of the HTTP response.
     */
     public Element asXml()
     {
    	 Element responseXml = null;

    	 try {
   	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
   	        DocumentBuilder builder = factory.newDocumentBuilder();
   	        Document document = builder.parse(this.responseStream);
   	        responseXml = document.getDocumentElement();

    	 } catch(Exception e) {
    		 System.out.print(e.toString());
    	 }

    	 return responseXml;
     }

}