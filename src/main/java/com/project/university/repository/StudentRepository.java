package com.project.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Student;

/**
 * @author Eugene The repository class contain methods working with data base
 */
@Repository
public class StudentRepository implements CrudRepository<Student> {

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
	public int save(Student student) {
		return this.jdbcTemplate.update("INSERT INTO STUDENTS (studentName, studentSurname, groupId) VALUES (?,?,?)",
				student.getStudentName(), student.getStudentSurname(), student.getGroupId());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Student find(int id) {
		return this.jdbcTemplate.queryForObject(
				"SELECT studentId, studentName, studentSurname " + "FROM STUDENTS WHERE studentId=?",
				BeanPropertyRowMapper.newInstance(Student.class), id);
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public int update(Student student) {
		return this.jdbcTemplate.update(
				"UPDATE STUDENTS SET studentName=?, studentSurname=? " + "WHERE studentId=?",
				student.getStudentName(), student.getStudentSurname(), student.getStudentId());
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public int delete(int id) {
		return this.jdbcTemplate.update("DELETE FROM STUDENTS WHERE studentId=?", id);
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Student> getAll() {
		return this.jdbcTemplate.query("SELECT studentId, studentName, studentSurname, groupId FROM STUDENTS", 
				BeanPropertyRowMapper.newInstance(Student.class));
	}
}

