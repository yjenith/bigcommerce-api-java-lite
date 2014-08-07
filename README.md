Bigcommerce API V2 Java Client
==============================

Java client for accessing the Bigcommerce REST API.

Installation
------------

Import the .jar file into your classpath, or download and compile the source
package in ./src

Usage
-----

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

		Orders orders = new Store(storeUrl, user, apiKey).getOrders();

		Collection<Order> allOrders = orders.listAll();

		for (Order order : allOrders) {
			System.out.println("Customer ID:" + order.getCustomerId());
			System.out.println("Order ID:" + order.getId());
			System.out.println("Order Status:" + order.getStatus());
		}
	}

}
```
