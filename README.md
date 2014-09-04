Bigcommerce API V2 Java Client
==============================

Java client for accessing the Bigcommerce REST API.

Installation
------------

Import the .jar file into your classpath, or download and compile the source
package in ./src

Required Library: commons-codec-1.3.jar, json-simple.jar

Getting Started
---------------
This is the simplest way to construct a Bigcommerce API client
	
	// Get Store API connection (Authentication)
	Store api = new Store(storeUrl, user, apiKey);

	// Get Orders resource to manage
	String url = "/orders";
	Resource<Order> ordersRes = api.getResource(Order.class, url, "order");

This example executes a GET request to the Bigcommece api:

	String storeUrl = "https://examplestore.com";
	String username = "admin";
	String apiKey   = "akjfalksjflksjflaskdjflasdk";

	// Get Store API connection
	Store api = new Store(storeUrl, user, apiKey);

	// Get Orders resource to manage
	String url = "/orders";
	Resource<Order> ordersRes = api.getResource(Order.class, url, "order");

	// Get an Order
	Order order = ordersRes.get(105);

Execute a GET Request to get a single object
------------------------------------------------------

	String url = "/orders";
	Resource<Order> ordersRes = api.getResource(Order.class, url, "order");
			
	// Get an Order
	Order order = ordersRes.get(105);

Execute a GET Request to get a list of objects
------------------------------------------------------

	String url = "/orders";
	Resource<Order> ordersRes = api.getResource(Order.class, url, "order");
	
	Collection<Order> orders = ordersRes.listAll();

Execute a POST Request on a single object
----------------------------------------------

	String url = "/orders";
	Resource<Order> ordersRes = api.getResource(Order.class, url, "order");
	
	// Form parameters
	Form form = Form.create();
	form.addProperty...
	form.addProperty...

	// Create
	Boolean status = ordersRes.create(form);

Execute a PUT Request on a single object
---------------------------------------------

	String url = "/orders";
	Resource<Order> ordersRes = api.getResource(Order.class, url, "order");

	// Form parameters
	Form form = Form.create();
	form.addProperty("status_id", 12);

	// Update
	Boolean status = ordersRes.update(107, form);


Using the store facade:

```
import com.bigcommerce.api.Store;

class BigcommerceApiTest
{

	public static void main(String[] args)
	{
		String storeUrl = "https://examplestore.com";
		String username = "admin";
		String apiKey   = "akjfalksjflksjflaskdjflasdk";
		
		// Get Store API connection
		Store api = new Store(storeUrl, user, apiKey);

		// Get Orders resource to manage
		String url = "/orders";
		Resource<Order> ordersRes = api.getResource(Order.class, url, "order");
	
		// Filter parameters
		Filter filter = Filter.create();
		filter.add("min_id", "107");
		Collection<Order> orders = ordersRes.listAll(filter);

		for (Order order : orders) {
			System.out.println("Customer ID:" + order.getCustomerId());
			System.out.println("Order ID:" + order.getId());
			System.out.println("Order Status:" + order.getStatus());

			System.out.println("---------------------");
		}
	}

}
```
See also: https://github.com/yjenith/bigcommerce-api-java | Unit test: https://github.com/yjenith/bigcommerce-api-java-lite/blob/master/src/test/java/com/bigcommerce/api/BigcommerceApiTest.java

