import com.bigcommerce.api.Connection;

public class Test
{

    public static void main(String[] args)
    {
        String storeUrl = "http://bigcommerce.local.localdomain";
    	String user = "admin";
    	String apiKey = "0959e1ea1b70a1c5c467d0809fac9312624014b3";

        Connection connection = new Connection(storeUrl, user, apiKey);
        String body = connection.get("/api/v1/time").asString();
        System.out.println(body);
    }

}