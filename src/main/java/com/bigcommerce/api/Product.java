package com.bigcommerce.api;

import org.w3c.dom.Element;

public class Product extends Response {

	public Product() {
		super();
	}
	
	public Product(Element document) {
		super(document);
	}

	public String getName() {
		return getScalarField("name");
	}

	public Integer getId() {
		String id = getScalarField("id");
		if (id != null) {
			return Integer.parseInt(id);
		} else {
			return null;
		}
	}

}
