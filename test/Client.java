import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

//import sun.misc.BASE64Encoder;

class Client
{

    public static String get()
    {
        HttpURLConnection connection = null;
		Element rootElement;

        StringBuffer sBuffer = new StringBuffer();

		try {

	        URL url = new URL("https://www.bigcommerce.com/");
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        //BASE64Encoder enc = new sun.misc.BASE64Encoder();
	        //String userpassword = this.username + ":" + this.password;
	        //String encodedAuthorization = enc.encode( userpassword.getBytes() );
	        //connection.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
	        //connection.setRequestProperty("Content-type", "application/xml");
	        //connection.setRequestProperty("Accept", "application/xml");

	        //InputStream responseStream = connection.getInputStream();

	        //connection.setDoOutput(true);
    		//OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
    		//wr.write(data);
    		//wr.flush();

    		InputStream in = connection.getInputStream();
    		BufferedReader res = new BufferedReader(new InputStreamReader(in, "UTF-8"));


    		String inputLine;
    		while ((inputLine = res.readLine()) != null) {
    			sBuffer.append(inputLine);
    		}

    		res.close();

	        //System.out.println(responseStream);

	        //--- Parse XML response InputStream into DOM

	        //DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        //DocumentBuilder db = dbf.newDocumentBuilder();

	        //Document doc = db.parse(responseStream);

	        //rootElement = doc.getDocumentElement();

		} catch(Exception e) {

			System.out.print(e.toString());
			rootElement = null;

		} finally {

			if(connection != null) {
	            connection.disconnect();
	        }

		}

		return sBuffer.toString();
    }

	public static void main(String[] args)
	{
	    String elm = Client.get();

		System.out.println(elm);
	}

}