package com.xtremedreamers.webuy.persistence;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, K extends Serializable> {
	List<T> findById(K id);

	List<T> findAll();

	List<T> find(T example);

	List<T> find(String queryName, String[] paramNames, Object[] bindValues);

	int count();

	List<T> getPagination(int pageNumber, int pageSize);
	
	List<T> getPagination(int pageNumber, int pageSize, String sortByName);

	K save(T instance);

	void update(T instance);

	void delete(T instance);
}