package com.bigcommerce.api;

import java.util.Collection;

import com.bigcommerce.api.resources.BaseResource;
import com.bigcommerce.api.resources.Resource;

public class ResourceTest {

	public static void main(String[] args) {
		String storeUrl = "https://store-nhze6w2d.mybigcommerce.com/api/v2";
		String user = "webops";
		String apiKey = "5fd83d1753c6dbd125da6b0433d1ec6620c5b2bf";

		// Get Store API connection
		Store api = new Store(storeUrl, user, apiKey);

		// Get Orders resource to manage
		StringBuffer uriPath = new StringBuffer("/orders");
		Resource<Order> ordersRes = api.getResource(Order.class,
				uriPath.toString(), "order");

		testListOrderWithNoFilter(ordersRes);
		testListOrderWithFilter(ordersRes);
		testGetAnOrder(ordersRes);
		testUpdateOrder(ordersRes);
		//testDeleteOrder(ordersRes);
	}

	/**
	 * 
	 * 
	 */
	public static void testGetAnOrder(Resource<Order> ordersRes) {
		Order order = null;

		// Set ID of the record
		ordersRes.setId(105);

		// Get an Order
		order = ordersRes.get();

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

		// Set ID of the record to be updated
		ordersRes.setId(107);

		// Form parameters
		Form form = Form.create();
		form.addProperty("status_id", 12);

		// Update
		Boolean status = ordersRes.update(form);

		System.out.println("Is Order Updated? " + status);
	}
	

	/**
	 * Test Order delete
	 * 
	 * @param api
	 */
	public static void testDeleteOrder(BaseResource<Order> ordersRes) {
		System.out.println("Test  Update Order-----");

		// Set ID of the record to be deleted
		ordersRes.setId(107);

		// Update
		Boolean status = ordersRes.delete();

		System.out.println("Is Order Deleted? " + status);
	}

}
