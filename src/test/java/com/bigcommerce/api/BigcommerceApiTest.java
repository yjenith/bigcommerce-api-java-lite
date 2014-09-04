package com.bigcommerce.api;

import java.util.Collection;

import com.bigcommerce.api.resources.BaseResource;
import com.bigcommerce.api.resources.Resource;

public class BigcommerceApiTest {

	public static void main(String[] args) {
		String storeUrl = "https://examplestore.com";
		String user = "admin";
		String apiKey = "akjfalksjflksjflaskdjflasdk";


		// Get Store API connection
		Store api = new Store(storeUrl, user, apiKey);

		// Get Orders resource to manage
		String uriPath = new String("/orders");
		Resource<Order> ordersRes = api.getResource(Order.class,
				uriPath, "order");

		testListOrderWithNoFilter(ordersRes);
		testListOrderWithFilter(ordersRes);
		testGetAnOrder(ordersRes);
		testUpdateOrder(ordersRes);
		testDeleteOrder(ordersRes);
	}

	/**
	 * Gets an Order
	 * 
	 */
	public static void testGetAnOrder(Resource<Order> ordersRes) {
		Order order = null;

		// Get an Order
		order = ordersRes.get(105);

		System.out.print(order.getId());
	}

	/**
	 * Test Orders with No Filter
	 * 
	 * @param api
	 */
	public static void testListOrderWithNoFilter(Resource<Order> ordersRes) {
		System.out.println("Test List Order without filter-----");
		Collection<Order> orders = ordersRes.listAll();

		for (Order order : orders) {
			System.out.println("Customer ID:" + order.getCustomerId());
			System.out.println("Order ID:" + order.getId());
			System.out.println("Order Status:" + order.getStatus());

			System.out.println("---------------------");
		}
	}

	/**
	 * Test Orders with Filter
	 * 
	 * @param api
	 */
	public static void testListOrderWithFilter(Resource<Order> ordersRes) {
		System.out.println("Test List Order with min_id filter-----");

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

	/**
	 * Test Order update Status
	 * 
	 * @param api
	 */
	public static void testUpdateOrder(Resource<Order> ordersRes) {
		System.out.println("Test  Update Order-----");
		
		// Form parameters
		Form form = Form.create();
		form.addProperty("status_id", 12);

		// Update
		Boolean status = ordersRes.update(107, form);

		System.out.println("Is Order Updated? " + status);
	}
	

	/**
	 * Test Order delete
	 * 
	 * @param api
	 */
	public static void testDeleteOrder(BaseResource<Order> ordersRes) {
		System.out.println("Test  Update Order-----");

		// Update
		Boolean status = ordersRes.delete(107);

		System.out.println("Is Order Deleted? " + status);
	}

}
