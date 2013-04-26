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
	public void testGetNameReturnsIdNodeText() {
		final Element document = context.mock(Element.class);
		final String expectedName = "Test Product";

		Product product = new Product(document);

		context.checking(createNodeTextExpectations(document, "name", expectedName));

		String actualName = product.getName();

		assertEquals(expectedName, actualName);
	}

	@Test
	public void testGetIdReturnsIdNodeText() {
		final Element document = context.mock(Element.class);
		final Integer expectedId = 1;

		Product product = new Product(document);

		context.checking(createNodeTextExpectations(document, "id", expectedId.toString()));

		Integer actualId = product.getId();

		assertEquals(expectedId, actualId);
	}

	protected Expectations createNodeTextExpectations(final Element document, final String name, final String text) {
		final Node nameNode = context.mock(Node.class);
		final NodeList nameNodeList = context.mock(NodeList.class);
		return new Expectations() {{
			oneOf(nameNode).getTextContent(); will(returnValue(text));
			oneOf(nameNodeList).item(0); will(returnValue(nameNode));
		    oneOf(document).getElementsByTagName(name); will(returnValue(nameNodeList));
		}};
	}
}
