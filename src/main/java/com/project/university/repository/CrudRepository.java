package com.project.university.repository;

import java.util.List;

/** @author Eugene
 * Interface for generic CRUD operations on a repository for a specific type.
 * @param <T>
 */
public interface CrudRepository<T> {
	
	/** Saves a given entity
	 * @param t - must not be null.
	 * @return the count saved line
	 */
	int save(T t);
	
	/** Retrieves an entity by its id.
	 * @param id - must not be null
	 * @return the given entity
	 */
	T find(int id);
	
	/** Update a given entity
	 * @param t - must not be null
	 * @return the count update line
	 */
	int update(T t);
	
	/** Deletes by given id. 
	 * @param id - must not be null
	 * @return the count delete line
	 */
	int delete(int id);
	
	/** Returns all instances of the type.
	 * @return all entities
	 */
	List<T> getAll();
}

