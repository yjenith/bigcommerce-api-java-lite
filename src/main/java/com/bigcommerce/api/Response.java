package com.bigcommerce.api;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Response {
	Element document;

	Response() {
	}

	Response(Element document) {
		this.document = document;
	}

	public void setDocument(Element document) {
		this.document = document;
	}

	public String getScalarField(String name) {
		NodeList elements = document.getElementsByTagName(name);
		Node field = elements.item(0);
		if (field != null) {
			return field.getTextContent();
		}
		return null;
	}

	public Element asXml() {
		return document;
	}
}
