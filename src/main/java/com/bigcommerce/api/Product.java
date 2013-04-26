package com.bigcommerce.api;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Product extends Resource
{

	public Product(Element document)
	{
		super(document);
	}
	
	public String getName()
	{
		NodeList elements = document.getElementsByTagName("name");
		Node price = elements.item(0);
		if (price != null) {
			return price.getTextContent();
		}
		return null;
	}

}