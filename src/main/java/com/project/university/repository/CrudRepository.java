package com.project.university.repository;

import java.sql.SQLException;
import java.util.List;

import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.repository.exception.DataSaveException;

/** @author Eugene
 * Interface for generic CRUD operations on a repository for a specific type.
 * @param <T>
 */
public interface CrudRepository<T> {
	
	/** Saves a given entity
	 * @param t - must not be null.
	 * @return the count saved line
	 * @throws DataAlreadyExistsException 
	 * @throws NoSuchTableException 
	 * @throws SQLException 
	 * @throws DataSaveException 
	 * @throws DaoLayerException 
	 */
	T save(T t) throws DataSaveException, DaoLayerException;
	
	/** Retrieves an entity by its id.
	 * @param id - must not be null
	 * @return the given entity
	 * @throws DataNotFoundException 
	 * @throws DaoLayerException 
	 * @throws SQLException 
	 */
	T findOneById(Integer id) throws DaoLayerException, DataNotFoundException;
	
	/** Update a given entity
	 * @param t - must not be null
	 * @return the count update line
	 * @throws DataNotFoundException 
	 * @throws DaoLayerException 
	 */
	T update(T t) throws DataNotFoundException, DaoLayerException;
	
	/** Deletes by given id. 
	 * @param id - must not be null
	 * @return the count delete line
	 * @throws DaoLayerException 
	 */
	void delete(T id) throws DataNotFoundException, DaoLayerException;
	
	/** Returns all instances of the type.
	 * @return all entities
	 * @throws DaoLayerException 
	 * @throws NoSuchTableException 
	 * @throws SQLException 
	 */
	List<T> getAll() throws DataNotFoundException, DaoLayerException;
}

