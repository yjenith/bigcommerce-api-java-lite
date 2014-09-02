package com.bigcommerce.api.resources;

import java.util.List;

import com.bigcommerce.api.Filter;
import com.bigcommerce.api.Form;
import com.bigcommerce.api.Response;

public interface Resource {
	public void setId(Integer id);
	public Boolean create(Form data);
	public Boolean update(Form data);
	public List<? extends Response> listAll();
	public List<? extends Response> listAll(Filter filter);
	public Response get();
	public Boolean delete();
}
