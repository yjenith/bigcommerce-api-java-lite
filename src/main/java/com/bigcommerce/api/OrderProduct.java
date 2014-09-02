package com.bigcommerce.api;

import org.w3c.dom.Element;

public class OrderProduct extends Response {

	public OrderProduct() {
		super();
	}
	
	
	public OrderProduct(Element document) {
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

	public Integer getProductId() {
		String productId = getScalarField("product_id");
		if (productId != null) {
			return Integer.parseInt(productId);
		} else {
			return null;
		}
	}

	public Integer getOrderAddressId() {
		String orderAddressId = getScalarField("order_address_id");
		if (orderAddressId != null) {
			return Integer.parseInt(orderAddressId);
		} else {
			return null;
		}
	}

	public Integer getQuantity() {
		String quantity = getScalarField("quantity");
		if (quantity != null) {
			return Integer.parseInt(quantity);
		} else {
			return null;
		}
	}

	public String getName() {
		return getScalarField("name");
	}

}
