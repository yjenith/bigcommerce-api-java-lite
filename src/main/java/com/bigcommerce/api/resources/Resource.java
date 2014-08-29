package com.bigcommerce.api.resources;

import java.util.List;

import com.bigcommerce.api.Filter;
import com.bigcommerce.api.Form;
import com.bigcommerce.api.Response;

public interface Resource {
	public List<? extends Response> listAll();
	public List<? extends Response> listAll(Filter filter);
	public Response getOne(Integer Id);
	public Boolean update(Integer Id, Form data);
}
