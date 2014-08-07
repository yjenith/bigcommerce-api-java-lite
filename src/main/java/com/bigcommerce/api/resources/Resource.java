package com.bigcommerce.api.resources;

import java.util.List;

import com.bigcommerce.api.Response;

public interface Resource {
	public List<? extends Response> listAll();
	public Response getOne(Integer Id);
}
