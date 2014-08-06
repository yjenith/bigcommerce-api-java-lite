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

		Store store = new Store(storeUrl, username, apiKey);

		Collection<Product> products = store.getProducts();

		for (Product product : products) {
			System.out.println("Product Name:" + product.getName());
			System.out.println("Product ID:" + product.getId());
		}
	}

}
```
