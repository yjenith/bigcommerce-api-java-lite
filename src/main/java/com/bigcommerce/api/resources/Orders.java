package com.bigcommerce.api.resources;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.bigcommerce.api.Connection;
import com.bigcommerce.api.Filter;
import com.bigcommerce.api.Form;
import com.bigcommerce.api.Order;
import com.bigcommerce.api.OrderProduct;
import com.bigcommerce.api.ShippingAddress;

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
	@Override
	public List<Order> listAll() {
		return this.listAll(null);
	}

	/**
	 * Gets the collection of orders.
	 */
	@Override
	public List<Order> listAll(Filter filter) {
		List<Order> orders = new ArrayList<Order>();

		StringBuffer path = new StringBuffer("/orders");
		if (filter != null) {
			path.append(filter.toQuery());
		}
		Element xml = this.connection.get(path.toString()).asXml();

		if (xml != null) {
			NodeList orderTags = xml.getElementsByTagName("order");
			for (int i = 0; i < orderTags.getLength(); i++) {
				Element orderTag = (Element) orderTags.item(i);
				Order order = new Order(orderTag);
				// order products
				order.setOrderProducts(this.listAllOrderProducts(order.getId()));
				// order shipping addresses
				order.setShippingAddresses(this.listAllShippingAddresses(order.getId()));
				orders.add(order);
			}
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
	@Override
	public Order getOne(Integer orderId) {
		Order order = null;
		StringBuffer path = new StringBuffer("/orders/" + orderId);
		Element xml = this.connection.get(path.toString()).asXml();

		if (xml != null) {
			NodeList orderTags = xml.getElementsByTagName("order");
			Element orderTag = (Element) orderTags.item(0);
			order = new Order(orderTag);
		}

		return order;
	}

	/**
	 * Gets the collection of order products.
	 */
	public List<OrderProduct> listAllOrderProducts(Integer orderId) {
		List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
		StringBuffer path = new StringBuffer("/orders/" + orderId);
		path.append("/products");
		Element xml = this.connection.get(path.toString()).asXml();

		if (xml != null) {
			NodeList orderProductTags = xml.getElementsByTagName("product");
			for (int i = 0; i < orderProductTags.getLength(); i++) {
				Element orderProductTag = (Element) orderProductTags.item(i);
				OrderProduct orderProduct = new OrderProduct(orderProductTag);
				orderProducts.add(orderProduct);
			}
		}

		return orderProducts;
	}

	/**
	 * Gets the collection of shipping addresses of an order.
	 */
	public List<ShippingAddress> listAllShippingAddresses(Integer orderId) {
		List<ShippingAddress> shippingAddresses = new ArrayList<ShippingAddress>();
		StringBuffer path = new StringBuffer("/orders/" + orderId);
		path.append("/shippingaddresses");
		Element xml = this.connection.get(path.toString()).asXml();

		if (xml != null) {
			NodeList shippingAddressTags = xml.getElementsByTagName("address");
			for (int i = 0; i < shippingAddressTags.getLength(); i++) {
				Element shippingAddrTag = (Element) shippingAddressTags.item(i);
				ShippingAddress shippingAddr = new ShippingAddress(shippingAddrTag);
				shippingAddresses.add(shippingAddr);
			}
		}

		return shippingAddresses;
	}

	@Override
	public Boolean update(Integer orderId, Form data) {
		StringBuffer path = new StringBuffer("/orders/" + orderId);
		Boolean result = this.connection.put(path.toString(), data.toJson());
		return result;
	}

}
