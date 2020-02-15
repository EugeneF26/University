package com.project.university.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.domain.StudentMapper;
import com.project.university.entity.Student;

/**
 * @author Eugene The repository class contain methods working with data base
 */
@Repository
public class StudentRepository implements CrudRepository<Student>, CrudStudentService{

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
		return this.jdbcTemplate.update("INSERT INTO STUDENTS (student_name, student_surname, group_id) VALUES (?,?,?)",
				student.getStudentName(), student.getStudentSurname(), student.getGroupId().getGroupId());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Student find(int id) {
		return this.jdbcTemplate.queryForObject(
				"SELECT student_id, student_name, student_surname " + "FROM STUDENTS WHERE student_id=?",
				BeanPropertyRowMapper.newInstance(Student.class), id);
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public int update(Student student) {
		return this.jdbcTemplate.update(
				"UPDATE STUDENTS SET student_name=?, student_surname=? " + "WHERE student_id=?",
				student.getStudentName(), student.getStudentSurname(), student.getStudentId());
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public int delete(int id) {
		return this.jdbcTemplate.update("DELETE FROM STUDENTS WHERE student_id=?", id);
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Student> getAll() {
		return this.jdbcTemplate.query("SELECT student_id, student_name, student_surname, group_id FROM STUDENTS", new StudentMapper());
	}
	
	/**
	 * regroup student
	 * @param student
	 * @return number of updated rows
	 */
	@Override
	public int transferOfStudentToAnotherGroup(Student student) {
		return this.jdbcTemplate.update("UPDATE STUDENTS SET group_id=?" + " WHERE student_id=?",
				student.getGroupId().getGroupId(), student.getStudentId());
	}
	
	/**
	 * Deletes all students from table
	 */
	@Override
	public void truncateStudentsTable() {		
		this.jdbcTemplate.update("DELETE FROM students");
	}
}

