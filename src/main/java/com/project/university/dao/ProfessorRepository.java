package com.project.university.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.SpringConfig;
import com.project.university.entity.Professor;

/** @author Eugene
 * The repository class contain methods working with data base
 */
@Repository
public class ProfessorRepository implements CrudRepository<Professor> {

	private JdbcTemplate jdbcTemplate;
	
	/** Construct a new JdbcTemplate, given a jdbcTemplate with DataSource to obtain connections from
	 * @param jdbcTemplate - the jdbcTemplate with DataSource to obtain connections from
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
	public int save(Professor professor) {
		return this.jdbcTemplate.update(
		        "INSERT INTO PROFESSORS (professor_name, professor_surname, professor_patronymic) VALUES (?,?,?)", 
		       professor.getName(), professor.getSurname(), professor.getPatronymic());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Professor find(int id) {
		return this.jdbcTemplate.queryForObject("SELECT professor_id, professor_name, professor_surname, professor_patronimyc "
				+ "FROM PROFESSORS WHERE professor_id = ?;", 
				 BeanPropertyRowMapper.newInstance(Professor.class), id);
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public int update(Professor professor) {
		return this.jdbcTemplate.update(
		        "UPDATE PROFESSOR SET professor_name=?, professor_surname=?, professor_patronymic=? "
		        + "WHERE professor_id=? ", professor.getName(), professor.getSurname(), 
		        		professor.getPatronymic(), professor.getProfessorId());
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public int delete(int id) {
		return this.jdbcTemplate.update(
		        "DELETE FROM PROFESSORS WHERE professor_id=?", id);
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Professor> getAll() {
		return this.jdbcTemplate.query("SELECT FROM PROFESSORS", BeanPropertyRowMapper.newInstance(Professor.class));
	}
}

