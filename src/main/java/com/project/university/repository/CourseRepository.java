package com.project.university.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.model.Course;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataSaveException;

/**
 * @author Eugene The repository class contain methods working with data base
 */
@Repository
public class CourseRepository implements CrudRepository<Course> {

	private JdbcTemplate jdbcTemplate;
	private Logger log = LoggerFactory.getLogger(CourseRepository.class.getName());

	/**
	 * Construct a new JdbcTemplate, given a jdbcTemplate with DataSource to obtain
	 * connections from
	 * 
	 * @param jdbcTemplate - the jdbcTemplate with DataSource to obtain connections
	 *                     from
	 * @see SpringConfig#dataSource()
	 */
	@Autowired
	public CourseRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @throws DaoLayerException 
	 * @throws DataSaveException 
	 * @see CrudRepository#save(Object)
	 */
	@Override
	public Course save(Course course) throws DaoLayerException, DataSaveException {
		Course result = null;
		int rows = 0;
		log.trace("entry with: {}", course);
		
		try {
		rows = this.jdbcTemplate.update("INSERT INTO COURSES (year) VALUES(?)", course.getYear());
		
		result = this.jdbcTemplate.queryForObject(
				"SELECT id FROM COURSES WHERE year=? ORDER BY id DESC LIMIT 1",
				BeanPropertyRowMapper.newInstance(Course.class), course.getYear());
		} catch (Exception ex) {
			log.error("Cannot add course to DB", ex);
			throw new DaoLayerException(CourseRepository.class.getName(), ex);
		}
		
		if (rows != 0) {
			log.trace("exit with: {}", course);
			return result;
		} else {
			log.warn("failed to create a new course {} with id {} ", course, course.getId());
			throw new DataSaveException("data was not saved");
		}
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Course findOneById(Integer year) {
		return this.jdbcTemplate.queryForObject("SELECT id, year FROM COURSES WHERE year = ?;",
				(rs, rowNum) -> {
					return Course.builder().id(rs.getInt("id")).year(rs.getInt("year")).build();
				}, year);
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public Course update(Course course) {
		this.jdbcTemplate.update("UPDATE COURSES SET year=? WHERE id=?", course.getYear(), course.getId());
		return course;
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public void delete(Course course) {
		this.jdbcTemplate.update("DELETE FROM COURSES WHERE id=?", course.getId());
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Course> getAll() {
		List<Course> result = null;
		result = this.jdbcTemplate.query("SELECT id, year FROM COURSES", (rs, rowNum) -> {
			return Course
					.builder()
					.id(rs.getInt("id"))
					.year(rs.getInt("year"))
					.build();
		});
		return result;
	}
}

