package com.project.university.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Professor;

@Repository
public class ProfessorRepository implements CrudRepository<Professor> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Professor professor) {
		return this.jdbcTemplate.update(
		        "INSERT INTO PROFESSORS (professor_name, professor_surname, professor_patronymic) VALUES (?,?,?)", 
		        new Object[] {professor.getName(), professor.getSurname(), professor.getPatronymic()});
	}

	@Override
	public Professor find(int id) {
		return this.jdbcTemplate.queryForObject("SELECT professor_id, professor_name, professor_surname, professor_patronimyc "
				+ "FROM PROFESSORS WHERE professor_id = ?;", 
				new Object[] {id}, BeanPropertyRowMapper.newInstance(Professor.class));
	}

	@Override
	public int update(Professor professor) {
		return this.jdbcTemplate.update(
		        "UPDATE PROFESSOR SET professor_name=?, professor_surname=?, professor_patronymic=? "
		        + "WHERE professor_id=? ", new Object[] {professor.getName(), professor.getSurname(), 
		        		professor.getPatronymic(), professor.getProfessorId()});
	}

	@Override
	public int delete(int id) {
		return this.jdbcTemplate.update(
		        "DELETE FROM PROFESSORS WHERE professor_id=?", new Object[] {id});
	}

	@Override
	public List<Professor> getAll() {
		return this.jdbcTemplate.query("SELECT FROM PROFESSORS", BeanPropertyRowMapper.newInstance(Professor.class));
	}
}

