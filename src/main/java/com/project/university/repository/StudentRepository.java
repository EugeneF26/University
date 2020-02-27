package com.project.university.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Group;
import com.project.university.entity.Student;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.repository.exception.DataSaveException;

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
	public Student save(Student student) throws DataSaveException, DaoLayerException {
		Student result = null;
		int rows = 0;
		String fullName = student.getName() + " " + student.getSurname();
		log.trace("adding professor's {} to Professors", fullName);
		try {
			rows = this.jdbcTemplate.update("INSERT INTO STUDENTS (name, surname, groupId) VALUES (?,?,?)", student.getName(),
					student.getSurname(), student.getGroup().getId());
			result = this.jdbcTemplate.queryForObject(
					"SELECT id FROM STUDENTS WHERE name=? AND surname=? AND groupId=?",
					BeanPropertyRowMapper.newInstance(Student.class), student.getName(), student.getSurname(),
					student.getGroup().getId());
		} catch (Exception ex) {
			log.error("Cannot add student's" + fullName + " to DB", ex);
			throw new DaoLayerException(StudentRepository.class.getName(), ex);
		}
		
		if(rows != 0) {
			log.trace("student {} successfully created with ID {}", fullName, result.getId());
			return result;
		} else {
			log.trace("failed to create a student's {} with id {} ", fullName, student.getId());
			throw new DataSaveException();
		}
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Student findOneById(Integer id) throws DataNotFoundException {
		Student result = null;
		log.trace("searching student's with id {}", id);
		try {
			result = this.jdbcTemplate.queryForObject("SELECT id, name, surname, groupId " + "FROM STUDENTS WHERE id=?",
					BeanPropertyRowMapper.newInstance(Student.class), id);
		} catch (Exception ex) {
			log.error("Method findOneById of StudentRepository threw an error", ex);
			throw new DataNotFoundException(ex);
		}
		if(result != null) {
			log.trace("student {} {} successfully find with ID {}", result.getName(), 
					result.getSurname(), result.getId());
			return result;
		} else {
			log.trace("query not returned data");
			return null;
		}
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public Student update(Student student) throws DataNotFoundException {
		int rows = 0;
		log.trace("updating student's with id {}", student.getId());
		try {
			rows = this.jdbcTemplate.update("UPDATE STUDENTS SET name=?, surname=?, groupId=? " + "WHERE id=?",
					student.getName(), student.getSurname(), student.getGroup().getId(), student.getId());
		} catch (Exception ex) {
			log.error("Method update of StudentRepository threw an error",ex);
			throw new DataNotFoundException(ex);
		}
		if(rows != 0) {
			log.trace("data of student's with id {} was updated", student.getId());
			return student;
		} else {
			log.trace("data of student's with id {} was not updated", student.getId());
			return null;
		}
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public void delete(Student student) throws DataNotFoundException {
		String fullName = student.getName() + " " + student.getSurname();
		int rows = 0;
		log.trace("deleting student's {} with id {}", fullName, student.getId());
		try {
			rows = this.jdbcTemplate.update("DELETE FROM STUDENTS WHERE id=?", student.getId());
		} catch(Exception ex) {
			log.error("Method delete of StudentRepository threw an error", ex);
			throw new DataNotFoundException(ex);
		}
		if(rows != 0) {
			log.trace("student {} successfully deleted with ID {}", fullName, student.getId());
		} else {
			log.trace("student {} with id {} was not deleted", fullName, student.getId());
		}
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Student> getAll() throws DataNotFoundException {
		List<Student> result = null;
		log.trace("getting list all students");
		try {
			result = this.jdbcTemplate.query("SELECT id, name, surname, groupId FROM STUDENTS", (rs, rowNum) -> {
				return Student.builder().id(rs.getInt("id")).name(rs.getString("name")).surname(rs.getString("surname"))
						.group(Group.builder().id(1).build()).build();
			});
		} catch(Exception ex) {
			log.error("Method getAll of StudentRepository threw an error", ex);
			throw new DataNotFoundException(ex);
		}
		
		if (result != null) {
			log.trace("list all students successfully created");
			return result;
		} else {
			log.trace("query not returned data");
			return null;
		}
	}
}

