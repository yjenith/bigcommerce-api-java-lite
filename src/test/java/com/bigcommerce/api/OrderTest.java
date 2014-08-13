package com.bigcommerce.api;

import java.util.Collection;

import com.bigcommerce.api.resources.Orders;

public class OrderTest {

	public static void main(String[] args) {
       		String storeUrl = "https://examplestore.com";
        	String use	= "admin";
        	String apiKey   = "akjfalksjflksjflaskdjflasdk";

		// Get Store API connection
		Store api = new Store(storeUrl, user, apiKey);

		listOrdersWithNoFilter(api);
		listOrdersWithFilter(api);
	}

	/**
	 * Test Orders with No Filter
	 * @param api
	 */
	public static void listOrdersWithNoFilter(Store api) {
		System.out.println("Order Test without filter-----");
		Collection<Order> orders = api.getOrders().listAll();

		for (Order order : orders) {
			System.out.println("Customer ID:" + order.getCustomerId());
			System.out.println("Order ID:" + order.getId());
			System.out.println("Order Status:" + order.getStatus());

			// List all products associated with an order
			for (OrderProduct orderProduct : order.getOrderProducts()) {
				System.out.println("Product ID:" + orderProduct.getId());
				System.out.println("Product Name:" + orderProduct.getName());
				System.out.println("Product Quantity:"
						+ orderProduct.getQuantity());
			}

			System.out.println("---------------------");
		}
	}

	/**
	 * Test Orders with Filter
	 * @param api
	 */
	public static void listOrdersWithFilter(Store api) {
		System.out.println("Order Test with min_id filter-----");
		
		// Filter parameters
		Filter filter = Filter.create();
		filter.add("min_id", "107");
		Collection<Order> orders = api.getOrders().listAll(filter);

		for (Order order : orders) {
			System.out.println("Customer ID:" + order.getCustomerId());
			System.out.println("Order ID:" + order.getId());
			System.out.println("Order Status:" + order.getStatus());

			// List all products associated with an order
			for (OrderProduct orderProduct : order.getOrderProducts()) {
				System.out.println("Product ID:" + orderProduct.getId());
				System.out.println("Product Name:" + orderProduct.getName());
				System.out.println("Product Quantity:"
						+ orderProduct.getQuantity());
			}

			System.out.println("---------------------");
		}

}
