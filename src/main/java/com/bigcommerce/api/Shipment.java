package com.bigcommerce.api;

import org.w3c.dom.Element;

public class Shipment extends Response {

	private String trackingNumber;
	private String comments;
	private Integer orderAddressId;

	public Shipment() {
		super();
	}

	public Shipment(Element document) {
		super(document);
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getOrderAddressId() {
		return orderAddressId;
	}

	public void setOrderAddressId(Integer orderAddressId) {
		this.orderAddressId = orderAddressId;
	}

}
