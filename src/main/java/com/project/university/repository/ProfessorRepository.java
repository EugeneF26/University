package com.project.university.repository;

import java.util.List;

import org.dbunit.dataset.NoSuchTableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Professor;
import com.project.university.entity.StatusProfessor;
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
	public Professor save(Professor professor) throws NoSuchTableException {
		Professor result = null;
		try {
			this.jdbcTemplate.update(
					"INSERT INTO PROFESSORS (name, surname, patronymic, currentStatus) VALUES (?,?,?,?)",
					professor.getName(), professor.getSurname(), professor.getPatronymic(),
					professor.getCurrentStatus().getStatus());
			result = this.jdbcTemplate.queryForObject(
					"SELECT id FROM PROFESSORS WHERE name=? AND surname=? AND patronymic=? " + "AND currentStatus=?",
					BeanPropertyRowMapper.newInstance(Professor.class), professor.getName(), professor.getSurname(),
					professor.getPatronymic(), professor.getCurrentStatus().getStatus());
		} catch (Exception ex) {
			log.error("Such table not exists", ex);
			throw new NoSuchTableException("Such table not exists", ex);
		}
		return result;
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Professor findOneById(Integer id) throws DataNotFoundException {
		Professor result = null;
		try {
			result = this.jdbcTemplate.queryForObject(
					"SELECT id, name, surname, patronymic " + "FROM PROFESSORS WHERE id = ?;",
					BeanPropertyRowMapper.newInstance(Professor.class), id);
		} catch (Exception ex) {
			log.error("Such data not exists", ex);
			throw new DataNotFoundException("Such data not exists", ex);
		}
		return result;
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public Professor update(Professor professor) throws DataNotFoundException {
		try {
			jdbcTemplate.update(
					"UPDATE PROFESSORS SET name=?, surname=?, patronymic=?, currentStatus=? " + "WHERE id=? ",
					professor.getName(), professor.getSurname(), professor.getPatronymic(),
					professor.getCurrentStatus().getStatus(), professor.getId());
		} catch (Exception ex) {
			log.error("Such data not exists", ex);
			throw new DataNotFoundException("Such data not exists", ex);
		}
		return professor;
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public void delete(Professor professor) throws DataNotFoundException {
		try {
			this.jdbcTemplate.update("DELETE FROM PROFESSORS WHERE id=?", professor.getId());
		} catch (Exception ex) {
			log.error("Such data not exists", ex);
			throw new DataNotFoundException("Such data not exists", ex);
		}
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Professor> getAll() throws NoSuchTableException {
		List<Professor> result = null;
		try {
			result = this.jdbcTemplate.query("SELECT * FROM PROFESSORS", (rs, rowNum) -> {
				return Professor.builder().id(rs.getInt("id")).name(rs.getString("name"))
						.surname(rs.getString("surname"))
						.currentStatus((StatusProfessor.valueOf((rs.getString("currentStatus"))))).build();
			});
		} catch (Exception ex) {
			log.error("Such data not exists", ex);
			throw new NoSuchTableException("Such data not exists", ex);
		}
		return result;
	}
}

