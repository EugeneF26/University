package com.project.university.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.model.Professor;
import com.project.university.model.StatusProfessor;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.repository.exception.DataSaveException;

/**
 * @author Eugene The repository class contain methods working with data base
 */
@Repository
public class ProfessorRepository implements CrudRepository<Professor> {

	private JdbcTemplate jdbcTemplate;
	private Logger log = LoggerFactory.getLogger(ProfessorRepository.class.getName());

	/**
	 * Construct a new JdbcTemplate, given a jdbcTemplate with DataSource to obtain
	 * connections from
	 * 
	 * @param jdbcTemplate - the jdbcTemplate with DataSource to obtain connections
	 *                     from
	 * @see SpringConfig#dataSource()
	 */
	@Autowired
	public ProfessorRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @throws DataSaveException 
	 * @throws DaoLayerException 
	 * @see CrudRepository#save(Object)
	 */
	@Override
	public Professor save(Professor professor) throws DataSaveException, DaoLayerException {
		Professor result = null;
		int rows = 0;
		log.trace("entry with: {}", professor);
		try {
			rows = this.jdbcTemplate.update(
					"INSERT INTO PROFESSORS (name, patronymic, currentStatus) VALUES (?,?,?)",
					professor.getName(), professor.getPatronymic(),
					professor.getCurrentStatus().getStatus());
			
			result = this.jdbcTemplate.queryForObject(
					"SELECT id FROM PROFESSORS WHERE name=? AND patronymic=? AND currentStatus=? ORDER BY id DESC LIMIT 1",
					BeanPropertyRowMapper.newInstance(Professor.class), professor.getName(),
					professor.getPatronymic(), professor.getCurrentStatus().getStatus());
			
		} catch (Exception ex) {
			log.error("Cannot add professor's to DB", ex);
			throw new DaoLayerException(ProfessorRepository.class.getName(), ex);
		}
		
		if(rows != 0) {
			log.trace("exit with: {}", professor);
			return result;
		} else {
			log.warn("failed to create a professor's {} with id {} ", professor, professor.getId());
			throw new DataSaveException("data was not saved");
		}
	}

	/**
	 * @throws DataNotFoundException 
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Professor findOneById(Integer id) throws DaoLayerException, DataNotFoundException  {
		Professor result = null;
		log.trace("entry with: {}", id);
		try {
			result = this.jdbcTemplate.queryForObject(
					"SELECT id, name, patronymic, currentStatus " + "FROM PROFESSORS WHERE id = ?",
					BeanPropertyRowMapper.newInstance(Professor.class), id);
		} catch (Exception ex) {
			log.error(ProfessorRepository.class.getName(), ex);
			throw new DaoLayerException (ProfessorRepository.class.getName(), ex);
		}
		
		if(result != null) {
			log.trace("exit with: {}", result);
			return result;
		} else {
			log.warn("query not returned data");
			throw new DataNotFoundException("data was not finded");
		}
	}

	/**
	 * @throws DaoLayerException 
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public Professor update(Professor professor) throws DataNotFoundException, DaoLayerException {
		int rows = 0;
		log.trace("entry with: {}", professor);
		try {
			rows = jdbcTemplate.update(
					"UPDATE PROFESSORS SET name=?, patronymic=?, currentStatus=? " + "WHERE id=? ",
					professor.getName(), professor.getPatronymic(),
					professor.getCurrentStatus().getStatus(), professor.getId());
		} catch (Exception ex) {
			log.error("Cannot update professor's to DB", ex);
			throw new DaoLayerException (ProfessorRepository.class.getName(), ex);
		}
		
		if(rows != 0) {
			log.trace("exit with: {}", professor);
			return professor;
		} else {
			log.warn("data of professor's with id {} was not updated", professor.getId());
			throw new DataNotFoundException("data was not updated");
		}
	}

	/**
	 * @throws DaoLayerException 
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public void delete(Professor professor) throws DataNotFoundException, DaoLayerException {
		int rows = 0;
		log.trace("entry with: {}", professor);
		try {
			rows = this.jdbcTemplate.update("DELETE FROM PROFESSORS WHERE id=?", professor.getId());
		} catch (Exception ex) {
			log.error("Cannot delete professor's to DB", ex);
			throw new DaoLayerException(ProfessorRepository.class.getName(), ex);
		}
		
		if(rows != 0) {
			log.trace("professor {} successfully deleted", professor);
		} else {
			log.warn("professor {} was not deleted", professor);
			throw new DataNotFoundException("data was not deleted");
		}
	}

	/**
	 * @throws DaoLayerException 
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Professor> getAll() throws DataNotFoundException, DaoLayerException {
		List<Professor> result = null;
		log.trace("getting list all professors");
		try {
			result = this.jdbcTemplate.query("SELECT id, name, patronymic, currentStatus FROM PROFESSORS", (rs, rowNum) -> {
				return Professor.builder().id(rs.getInt("id")).name(rs.getString("name")).patronymic(rs.getString("patronymic"))
						.currentStatus((StatusProfessor.valueOf((rs.getString("currentStatus")))))
						.build();
			});
		} catch (Exception ex) {
			log.error("Cannot get list professors from DB", ex);
			throw new DaoLayerException(ProfessorRepository.class.getName(), ex);
		}
		
		if (result != null) {
			log.trace("list all professors successfully created");
			return result;
		} else {
			log.warn("query not returned data");
			throw new DataNotFoundException("data was not getted");
		}
	}
}

