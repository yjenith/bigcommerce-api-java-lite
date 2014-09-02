package com.bigcommerce.api.resources;

import java.util.List;

import com.bigcommerce.api.Filter;
import com.bigcommerce.api.Form;

public interface Resource<T> {
	public void setId(Integer id);
	public Boolean create(Form data);
	public Boolean update(Form data);
	public List<T> listAll();
	public List<T> listAll(Filter filter);
	public T get();
	public Boolean delete();
	
}
