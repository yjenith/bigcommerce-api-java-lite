package com.bigcommerce.api;

import java.util.Collection;

import com.bigcommerce.api.resources.Orders;

public class OrderTest {

	public static void main(String[] args) {
		String storeUrl = "";
		String user = "";
		String apiKey = "";

		Store store = new Store(storeUrl, user, apiKey);
		Orders ordersResource = store.getOrdersResource();

		Collection<Order> orders = ordersResource.listAll();

		for (Order order : orders) {
			System.out.println("Customer ID:" + order.getCustomerId());
			System.out.println("Order ID:" + order.getId());
			System.out.println("Order Status:" + order.getStatus());
		}
	}

}
