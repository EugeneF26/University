package com.project.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Course;

/**
 * @author Eugene The repository class contain methods working with data base
 */
@Repository
public class CourseRepository implements CrudRepository<Course> {

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
	public Course save(Course course) {
		this.jdbcTemplate.update("INSERT INTO COURSES (year) VALUES(?)", course.getYear());
		return this.jdbcTemplate.queryForObject("SELECT id FROM COURSES WHERE id=?",
				BeanPropertyRowMapper.newInstance(Course.class), course.getId());
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
		this.jdbcTemplate.update("DELETE FROM COURSES WHERE id=? AND year = ?", course.getId(),
				course.getYear());
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Course> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM COURSES", BeanPropertyRowMapper.newInstance(Course.class));
	}
}

