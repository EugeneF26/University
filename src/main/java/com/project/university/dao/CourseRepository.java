package com.project.university.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Course;

/** @author Eugene
 * The repository class contain methods working with data base
 */
@Repository
public class CourseRepository implements CrudRepository<Course> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @see CrudRepository#save(Object)
	 */
	@Override
	public int save(Course course) {
		return this.jdbcTemplate.update(
		        "INSERT INTO COURSES (course_year) VALUES (?)", course.getYear());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Course find(int year) {
		return new Course(this.jdbcTemplate.queryForObject("SELECT course_year FROM COURSES WHERE course_year = ?;", 
				Integer.class, year));
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public int update(Course course) {
		return this.jdbcTemplate.update(
		        "UPDATE COURSES SET course_year=? WHERE course_year=? ", course.getYear());
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public int delete(int year) {
		return this.jdbcTemplate.update(
		        "DELETE FROM COURSES WHERE course_year = ?", year);
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Course> getAll() {
		return this.jdbcTemplate.query("SELECT FROM COURSES", BeanPropertyRowMapper.newInstance(Course.class));
	}
}

