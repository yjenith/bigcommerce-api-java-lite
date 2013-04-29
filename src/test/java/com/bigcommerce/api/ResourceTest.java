package com.bigcommerce.api;

import static org.junit.Assert.*;

import org.jmock.Mockery;
import org.junit.Test;
import org.w3c.dom.Element;

/**
 * A simple test that serves as an example and starting point for building out
 * the test suite.
 */
public class ResourceTest {

	Mockery context = new Mockery();

	@Test
	public void testConstructorAssignsProvidedDocument() {
		final Element document = context.mock(Element.class);

		Resource resource = new Resource(document);
		assertSame(document, resource.document);
	}

}
