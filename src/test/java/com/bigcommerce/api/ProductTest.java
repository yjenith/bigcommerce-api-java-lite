package com.bigcommerce.api;

import static org.junit.Assert.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ProductTest {
	
	Mockery context = new Mockery();

	@Test
	public void testGetNameReturnsNameNodeText() {
		final Element document = context.mock(Element.class);
		final Node nameNode = context.mock(Node.class);
		final NodeList nameNodeList = context.mock(NodeList.class);
		final String expectedName = "Test Product";
		
		Product product = new Product(document);
		
		context.checking(new Expectations() {{
			oneOf(nameNode).getTextContent(); will(returnValue(expectedName));
			oneOf(nameNodeList).item(0); will(returnValue(nameNode));
		    oneOf(document).getElementsByTagName("name"); will(returnValue(nameNodeList));
		}});
		
		String actualName = product.getName();
		
		assertEquals(expectedName, actualName);
	}
}
