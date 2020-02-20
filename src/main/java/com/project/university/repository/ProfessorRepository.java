package com.project.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Professor;
import com.project.university.entity.StatusProfessor;

/**
 * @author Eugene The repository class contain methods working with data base
 */
@Repository
public class ProfessorRepository implements CrudRepository<Professor> {

	private JdbcTemplate jdbcTemplate;

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
	public Professor save(Professor professor) {
		this.jdbcTemplate.update("INSERT INTO PROFESSORS (name, surname, patronymic, currentStatus) VALUES (?,?,?,?)",
				professor.getName(), professor.getSurname(), professor.getPatronymic(), 
				professor.getCurrentStatus().getStatus().toString());
		return this.jdbcTemplate.queryForObject("SELECT id FROM PROFESSORS WHERE name=? AND surname=? AND patronymic=? "
				+ "AND currentStatus=?",
				BeanPropertyRowMapper.newInstance(Professor.class), professor.getName(), professor.getSurname(),
				professor.getPatronymic(), professor.getCurrentStatus().getStatus().toString());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Professor findOneById(Integer id) {
		return this.jdbcTemplate.queryForObject(
				"SELECT id, name, surname, patronymic " + "FROM PROFESSORS WHERE id = ?;",
				BeanPropertyRowMapper.newInstance(Professor.class), id);
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public Professor update(Professor professor) {
		this.jdbcTemplate.update("UPDATE PROFESSORS SET name=?, surname=?, patronymic=?, currentStatus=? " + "WHERE id=? ",
				professor.getName(), professor.getSurname(), professor.getPatronymic(), 
				professor.getCurrentStatus().getStatus(), professor.getId());
		return professor;
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public void delete(Professor professor) {
		this.jdbcTemplate.update("DELETE FROM PROFESSORS WHERE id=?", professor.getId());
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Professor> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM PROFESSORS", (rs, rowNum) -> {
			return Professor
					.builder()
					.id(rs.getInt("id"))
					.name(rs.getString("name"))
					.surname(rs.getString("surname"))
					.currentStatus((StatusProfessor.valueOf((rs.getString("currentStatus")))))
					.build();
		});
	}
}

