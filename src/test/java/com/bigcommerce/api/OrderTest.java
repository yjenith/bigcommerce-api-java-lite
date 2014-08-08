package com.bigcommerce.api;

import java.util.Collection;

import com.bigcommerce.api.resources.Orders;

public class OrderTest {

	public static void main(String[] args) {
       		String storeUrl = "https://examplestore.com";
        	String use	= "admin";
        	String apiKey   = "akjfalksjflksjflaskdjflasdk";

		Store storeApi = new Store(storeUrl, user, apiKey);
		Orders orders = storeApi.getOrders();

		Collection<Order> allOrders = orders.listAll();

		
		for (Order order : orders) {
			System.out.println("Customer ID:" + order.getCustomerId());
			System.out.println("Order ID:" + order.getId());
			System.out.println("Order Status:" + order.getStatus());
			
			// List all products associated with an order
			for (OrderProduct orderProduct : order.getOrderProducts()) {
				System.out.println("Product ID:" + orderProduct.getId());
				System.out.println("Product Name:" + orderProduct.getName());
				System.out.println("Product Quantity:" + orderProduct.getQuantity());
			}
			
			System.out.println("---------------------");
		}
	}

}
