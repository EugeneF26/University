package com.project.university.dao;

import java.util.List;

public interface CrudRepository<T> {
	int save(T t);
	T find(int id);
	int update(T t);
	int delete(int id);
	List<T> getAll();
}

