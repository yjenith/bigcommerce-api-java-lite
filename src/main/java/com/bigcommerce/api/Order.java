package com.bigcommerce.api;

import java.util.List;

import org.w3c.dom.Element;

public class Order extends Response {

	private List<OrderProduct> orderProducts;
	private List<ShippingAddress> shippingAddresses;
	
	public Order() {
		super();
	}
	
	public Order(Element document) {
		super(document);
	}

	public String getCustomerId() {
		return getScalarField("customer_id");
	}

	public Integer getId() {
		String id = getScalarField("id");
		if (id != null) {
			return Integer.parseInt(id);
		} else {
			return null;
		}
	}

	public String getStatus() {
		return getScalarField("status");
	}

	public Integer getItemsTotal() {
		String itemsTotal = getScalarField("items_total");
		if (itemsTotal != null) {
			return Integer.parseInt(itemsTotal);
		} else {
			return null;
		}
	}

	public Integer getItemsShipped() {
		String itemsShipped = getScalarField("items_shipped");
		if (itemsShipped != null) {
			return Integer.parseInt(itemsShipped);
		} else {
			return null;
		}
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public List<ShippingAddress> getShippingAddresses() {
		return shippingAddresses;
	}

	public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
	}

}
