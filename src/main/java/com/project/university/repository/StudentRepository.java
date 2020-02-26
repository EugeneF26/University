package com.project.university.repository;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Group;
import com.project.university.entity.Student;
import com.project.university.exception.DataNotFoundException;

/**
 * @author Eugene The repository class contain methods working with data base
 */
@Repository
public class StudentRepository implements CrudRepository<Student> {

	private JdbcTemplate jdbcTemplate;
	private Logger log = LoggerFactory.getLogger(StudentRepository.class.getName());

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
	public Student save(Student student) throws SQLException {
		Student result = null;
		try {
			this.jdbcTemplate.update("INSERT INTO STUDENTS (name, surname, groupId) VALUES (?,?,?)", student.getName(),
					student.getSurname(), student.getGroup().getId());
			result = this.jdbcTemplate.queryForObject(
					"SELECT id FROM STUDENTS WHERE name=? AND surname=? AND groupId=?",
					BeanPropertyRowMapper.newInstance(Student.class), student.getName(), student.getSurname(),
					student.getGroup().getId());
		} catch (Exception ex) {
			log.error("Such table not exists", ex);
			throw new SQLException("Such table not exists", ex);
		}
		return result;
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Student findOneById(Integer id) throws DataNotFoundException {
		Student result = null;
		try {
			result = this.jdbcTemplate.queryForObject("SELECT id, name, surname, groupId " + "FROM STUDENTS WHERE id=?",
					BeanPropertyRowMapper.newInstance(Student.class), id);
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
	public Student update(Student student) throws DataNotFoundException {
		try {
			this.jdbcTemplate.update("UPDATE STUDENTS SET name=?, surname=?, groupId=? " + "WHERE id=?",
					student.getName(), student.getSurname(), student.getGroup().getId(), student.getId());
		} catch (Exception ex) {
			log.error("Such data not exists", ex);
			throw new DataNotFoundException("Such data not exists", ex);
		}
		return student;
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public void delete(Student student) throws DataNotFoundException {
		try {
			this.jdbcTemplate.update("DELETE FROM STUDENTS WHERE id=?", student.getId());
		} catch(Exception ex) {
			log.error("Such data not exists", ex);
			throw new DataNotFoundException("Such data not exists", ex);
		}
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Student> getAll() throws SQLException {
		List<Student> result = null;
		try {
			result = this.jdbcTemplate.query("SELECT id, name, surname, groupId FROM STUDENTS", (rs, rowNum) -> {
				return Student.builder().id(rs.getInt("id")).name(rs.getString("name")).surname(rs.getString("surname"))
						.group(Group.builder().id(1).build()).build();
			});
		} catch(Exception ex) {
			log.error("Such data not exists",ex);
			throw new SQLException("Such data not exists",ex);
		}
		return result;
	}
}

