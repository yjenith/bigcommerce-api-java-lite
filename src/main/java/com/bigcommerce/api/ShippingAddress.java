package com.bigcommerce.api;

import org.w3c.dom.Element;

public class ShippingAddress extends Response {

	public ShippingAddress(Element document) {
		super(document);
	}

	public Integer getId() {
		String id = getScalarField("id");
		if (id != null) {
			return Integer.parseInt(id);
		} else {
			return null;
		}
	}

	public Integer getOrderId() {
		String orderId = getScalarField("order_id");
		if (orderId != null) {
			return Integer.parseInt(orderId);
		} else {
			return null;
		}
	}

	public String getFirstName() {
		return getScalarField("first_name");
	}

}
