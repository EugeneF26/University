package com.project.university.repository;

import java.util.List;

import org.dbunit.dataset.NoSuchTableException;

import com.project.university.exception.DataAlreadyExistsException;
import com.project.university.exception.DataNotFoundException;

/** @author Eugene
 * Interface for generic CRUD operations on a repository for a specific type.
 * @param <T>
 */
public interface CrudRepository<T> {
	
	/** Saves a given entity
	 * @param t - must not be null.
	 * @return the count saved line
	 * @throws DataAlreadyExistsException 
	 */
	T save(T t) throws DataAlreadyExistsException ;
	
	/** Retrieves an entity by its id.
	 * @param id - must not be null
	 * @return the given entity
	 * @throws DataNotFoundException 
	 */
	T findOneById(Integer id) throws DataNotFoundException;
	
	/** Update a given entity
	 * @param t - must not be null
	 * @return the count update line
	 * @throws DataNotFoundException 
	 */
	T update(T t) throws DataNotFoundException;
	
	/** Deletes by given id. 
	 * @param id - must not be null
	 * @return the count delete line
	 */
	void delete(T id) throws DataNotFoundException;
	
	/** Returns all instances of the type.
	 * @return all entities
	 * @throws NoSuchTableException 
	 */
	List<T> getAll() throws NoSuchTableException;
}

