package com.project.university.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Student;

@Repository
public class StudentRepository implements CrudRepository<Student>{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Student student) {
		return this.jdbcTemplate.update(
		        "INSERT INTO STUDENTS (student_id, student_name, student_surname) VALUES (?,?,?)", 
		        new Object[] {student.getStudentId(), student.getName(), student.getSurname()});
	}

	@Override
	public Student find(int id) {
		return this.jdbcTemplate.queryForObject("SELECT student_id, student_name, student_surname "
				+ "FROM STUDENTS WHERE student_id = ?;", 
				new Object[] {id}, BeanPropertyRowMapper.newInstance(Student.class));
	}

	@Override
	public int update(Student student) {
		return this.jdbcTemplate.update(
		        "UPDATE STUDENTS SET student_id=?, student_name=?, student_surname=? "
		        + "WHERE student_id=? ", new Object[] {student.getName(), student.getSurname(), student.getStudentId()});
	}

	@Override
	public int delete(int id) {
		return this.jdbcTemplate.update(
		        "DELETE FROM STUDENTS WHERE student_id=?", new Object[] {id});
	}

	@Override
	public List<Student> getAll() {
		return this.jdbcTemplate.query("SELECT FROM STUDENTS", BeanPropertyRowMapper.newInstance(Student.class));
	}
}

