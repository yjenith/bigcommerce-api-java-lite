package com.bigcommerce.api;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Facade for accessing a BigCommerce store via the REST API.
 */
public class Store
{
    private Connection connection;

    public Store(String storeurl, String username, String apikey)
    {
        this.connection = new Connection(storeurl, username, apikey);
    }

    /**
     * Products collection
     */
    public List<Product> getProducts()
    {
        List products = new ArrayList<Product>();
        Element xml = this.connection.get("/products").asXml();

        NodeList productTags = xml.getElementsByTagName("product");
        for (int i=0; i<productTags.getLength(); i++) {
        	Element productTag = (Element) productTags.item(i);
        	Product product = new Product(productTag);
        	products.add(product);
        }

        return products;
    }

}