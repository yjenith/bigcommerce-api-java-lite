package com.bigcommerce.api;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class Resource {
	Element document;

	Resource(Element document) {
		this.document = document;
	}

	protected String getScalarField(String name) {
		NodeList elements = document.getElementsByTagName(name);
		Node field = elements.item(0);
		if (field != null) {
			return field.getTextContent();
		}
		return null;
	}
}
