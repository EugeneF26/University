package com.project.university.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Student;

/** @author Eugene
 * The repository class contain methods working with data base
 */
@Repository
public class StudentRepository implements CrudRepository<Student>{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @see CrudRepository#save(Object)
	 */
	@Override
	public int save(Student student) {
		return this.jdbcTemplate.update(
		        "INSERT INTO STUDENTS (student_id, student_name, student_surname) VALUES (?,?,?)", 
		        student.getStudentId(), student.getName(), student.getSurname());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Student find(int id) {
		return new Student(this.jdbcTemplate.queryForObject("SELECT student_id, student_name, student_surname "
				+ "FROM STUDENTS WHERE student_id = ?;", Integer.class, id));
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public int update(Student student) {
		return this.jdbcTemplate.update(
		        "UPDATE STUDENTS SET student_id=?, student_name=?, student_surname=? "
		        + "WHERE student_id=? ", student.getName(), student.getSurname(), student.getStudentId());
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public int delete(int id) {
		return this.jdbcTemplate.update(
		        "DELETE FROM STUDENTS WHERE student_id=?", id);
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Student> getAll() {
		return this.jdbcTemplate.query("SELECT FROM STUDENTS", BeanPropertyRowMapper.newInstance(Student.class));
	}
}

