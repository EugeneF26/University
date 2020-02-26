package com.project.university.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Professor;
import com.project.university.entity.StatusProfessor;
import com.project.university.exception.DataAlreadyExistsException;
import com.project.university.exception.DataNotFoundException;

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
	public void setDataSource(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @see CrudRepository#save(Object)
	 */
	@Override
	public Professor save(Professor professor) throws DataAlreadyExistsException {
		Professor result = null;
		int rows = 0;
		String fullName = professor.getName() + " " + professor.getSurname() + " " + professor.getPatronymic();
		log.trace("adding professor's {} to Professors", fullName);
		try {
			rows = this.jdbcTemplate.update(
					"INSERT INTO PROFESSORS (name, surname, patronymic, currentStatus) VALUES (?,?,?,?)",
					professor.getName(), professor.getSurname(), professor.getPatronymic(),
					professor.getCurrentStatus().getStatus());
			result = this.jdbcTemplate.queryForObject(
					"SELECT id FROM PROFESSORS WHERE name=? AND surname=? AND patronymic=? " + "AND currentStatus=?",
					BeanPropertyRowMapper.newInstance(Professor.class), professor.getName(), professor.getSurname(),
					professor.getPatronymic(), professor.getCurrentStatus().getStatus());
		} catch (Exception ex) {
			log.error("Cannot add professor's" + fullName + " to DB", ex);
			throw new DataAlreadyExistsException(ex);
		}
		
		if(rows != 0) {
			log.trace("professor {} successfully created with ID {}", fullName, result.getId());
			return result;
		} else {
			log.trace("failed to create a professor's {} with id {} ", fullName, professor.getId());
			return null;
		}
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Professor findOneById(Integer id) throws DataNotFoundException {
		Professor result = null;
		log.trace("searching professor's with id {}", id);
		try {
			result = this.jdbcTemplate.queryForObject(
					"SELECT id, name, surname, patronymic " + "FROM PROFESSORS WHERE id = ?;",
					BeanPropertyRowMapper.newInstance(Professor.class), id);
		} catch (Exception ex) {
			log.error("Method findOneById of ProfessorRepository threw an error", ex);
			throw new DataNotFoundException(ex);
		}
		
		if(result != null) {
			log.trace("professor {} {} {} successfully find with ID {}", result.getName(), result.getSurname(),
					result.getPatronymic(), result.getId());
			return result;
		} else {
			log.trace("query not returned data");
			return null;
		}
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public Professor update(Professor professor) throws DataNotFoundException {
		int rows = 0;
		log.trace("updating professor's with id {}", professor.getId());
		try {
			rows = jdbcTemplate.update(
					"UPDATE PROFESSORS SET name=?, surname=?, patronymic=?, currentStatus=? " + "WHERE id=? ",
					professor.getName(), professor.getSurname(), professor.getPatronymic(),
					professor.getCurrentStatus().getStatus(), professor.getId());
		} catch (Exception ex) {
			log.error("Method update of ProfessorRepository threw an error", ex);
			throw new DataNotFoundException(ex);
		}
		
		if(rows != 0) {
			log.trace("data of professor's with id {} was updated", professor.getId());
			return professor;
		} else {
			log.trace("data of professor's with id {} was not updated", professor.getId());
			return null;
		}
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public void delete(Professor professor) throws DataNotFoundException {
		String fullName = professor.getName() + " " + professor.getSurname() + " " + professor.getPatronymic();
		int rows = 0;
		log.trace("deleting professor's {} with id {}", fullName, professor.getId());
		try {
			rows = this.jdbcTemplate.update("DELETE FROM PROFESSORS WHERE id=?", professor.getId());
		} catch (Exception ex) {
			log.error("Method delete of ProfessorRepository threw an error", ex);
			throw new DataNotFoundException(ex);
		}
		
		if(rows != 0) {
			log.trace("professor {} successfully deleted with ID {}", fullName, professor.getId());
		} else {
			log.trace("professor {} with id {} was not deleted", fullName, professor.getId());
		}
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Professor> getAll() throws DataNotFoundException {
		List<Professor> result = null;
		log.trace("getting list all professors");
		try {
			result = this.jdbcTemplate.query("SELECT * FROM PROFESSORS", (rs, rowNum) -> {
				return Professor.builder().id(rs.getInt("id")).name(rs.getString("name"))
						.surname(rs.getString("surname"))
						.currentStatus((StatusProfessor.valueOf((rs.getString("currentStatus"))))).build();
			});
		} catch (Exception ex) {
			log.error("Method getAll of ProfessorRepository threw an error", ex);
			throw new DataNotFoundException(ex);
		}
		
		if (result != null) {
			log.trace("list all professors successfully created");
			return result;
		} else {
			log.trace("query not returned data");
			return null;
		}
	}
}

