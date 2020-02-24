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
	T save(T t);
	
	/** Retrieves an entity by its id.
	 * @param id - must not be null
	 * @return the given entity
	 */
	T findOneById(Integer id);
	
	/** Update a given entity
	 * @param t - must not be null
	 * @return the count update line
	 */
	T update(T t);
	
	/** Deletes by given id. 
	 * @param id - must not be null
	 * @return the count delete line
	 */
	void delete(T id);
	
	/** Returns all instances of the type.
	 * @return all entities
	 */
	List<T> getAll();
}

