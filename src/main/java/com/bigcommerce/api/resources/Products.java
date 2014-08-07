package com.bigcommerce.api.resources;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.bigcommerce.api.Connection;
import com.bigcommerce.api.Product;

/**
 * Facade for managing a Bigcommerce store Products Resource via the REST API.
 */
public class Products implements Resource {
	private Connection connection;

	/**
	 * A simple constructor that accepts a store url connection.
	 * {@link com.bigcommerce.api.auth.Credentials Credentials}.
	 * 
	 * @param connection
	 * 
	 */
	public Products(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Gets the collection of products.
	 */
	public List<Product> listAll() {
		List<Product> products = new ArrayList<Product>();
		Element xml = this.connection.get("/products").asXml();

		NodeList productTags = xml.getElementsByTagName("product");
		for (int i = 0; i < productTags.getLength(); i++) {
			Element productTag = (Element) productTags.item(i);
			Product product = new Product(productTag);
			products.add(product);
		}

		return products;
	}

	/**
	 * 
	 * Gets a product.
	 * 
	 * @param orderId
	 * @return
	 */
	public Product getOne(Integer productId) {
		Product product = null;
		StringBuffer path = new StringBuffer("/products/" + productId);
		Element xml = this.connection.get(path.toString()).asXml();

		NodeList productTags = xml.getElementsByTagName("product");
		Element productTag = (Element) productTags.item(0);
		product = new Product(productTag);

		return product;
	}

}
