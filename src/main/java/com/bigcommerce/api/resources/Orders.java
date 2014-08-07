package com.bigcommerce.api.resources;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.bigcommerce.api.Connection;
import com.bigcommerce.api.Order;

/**
 * Facade for managing a Bigcommerce store Orders Resource via the REST API..
 */
public class Orders implements Resource {
	private Connection connection;

	/**
	 * A simple constructor that accepts a store url connection.
	 * {@link com.bigcommerce.api.auth.Credentials Credentials}.
	 * 
	 * @param connection
	 * 
	 */
	public Orders(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Gets the collection of orders.
	 */
	public List<Order> listAll() {
		List<Order> orders = new ArrayList<Order>();
		Element xml = this.connection.get("/orders").asXml();

		NodeList orderTags = xml.getElementsByTagName("order");
		for (int i = 0; i < orderTags.getLength(); i++) {
			Element orderTag = (Element) orderTags.item(i);
			Order order = new Order(orderTag);
			orders.add(order);
		}

		return orders;
	}

	/**
	 * 
	 * Gets an order.
	 * 
	 * @param orderId
	 * @return
	 */
	public Order getOne(Integer orderId) {
		Order order = null;
		StringBuffer path = new StringBuffer("/orders/" + orderId);
		Element xml = this.connection.get(path.toString()).asXml();

		NodeList orderTags = xml.getElementsByTagName("order");
		Element orderTag = (Element) orderTags.item(0);
		order = new Order(orderTag);

		return order;
	}

}
