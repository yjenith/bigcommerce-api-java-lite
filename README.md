BigCommerce API V2 Java Client
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

class BigCommerceApiTest
{

	public static void main(String[] args)
	{
		String storeUrl = "https://examplestore.com";
		String username = "admin";
		String apiKey   = "akjfalksjflksjflaskdjflasdk"

		Store store = new Store(storeUrl, user, apiKey);

		Collection<Product> products = store.getProducts();

		for(product in products) {
			System.out.println(product.getName());
			System.out.println(product.getPrice());
		}
	}

}
```