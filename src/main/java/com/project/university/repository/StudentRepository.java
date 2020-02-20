package com.project.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Group;
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
	public Student save(Student student) {
		this.jdbcTemplate.update("INSERT INTO STUDENTS (name, surname, groupId) VALUES (?,?,?)", student.getName(),
				student.getSurname(), student.getGroup().getId());
		return this.jdbcTemplate.queryForObject("SELECT id FROM STUDENTS WHERE name=? AND surname=? AND groupId=?",
				BeanPropertyRowMapper.newInstance(Student.class), student.getName(), student.getSurname(), 
				student.getGroup().getId());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Student findOneById(Integer id) {
		return this.jdbcTemplate.queryForObject("SELECT id, name, surname, groupId " + "FROM STUDENTS WHERE id=?",
				BeanPropertyRowMapper.newInstance(Student.class), id);
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public Student update(Student student) {
		this.jdbcTemplate.update("UPDATE STUDENTS SET name=?, surname=?, groupId=? " + "WHERE id=?", student.getName(),
				student.getSurname(), student.getGroup().getId(), student.getId());
		return student;
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public void delete(Student student) {
		this.jdbcTemplate.update("DELETE FROM STUDENTS WHERE id=?", student.getId());
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Student> getAll() {
		return this.jdbcTemplate.query("SELECT id, name, surname, groupId FROM STUDENTS", (rs, rowNum) -> {
			return Student
					.builder()
					.id(rs.getInt("id"))
					.name(rs.getString("name"))
					.surname(rs.getString("surname"))
					.group(Group
							.builder()
							.id(1)
							.build())
					.build();
		});
	}
}

