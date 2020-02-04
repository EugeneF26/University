package com.project.university.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Course;

@Repository
public class CourseRepository implements CrudRepository<Course> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Course course) {
		return this.jdbcTemplate.update(
		        "INSERT INTO COURSES (course_year) VALUES (?)", course.getYear());
	}

	@Override
	public Course find(int year) {
		return this.jdbcTemplate.queryForObject("SELECT course_year FROM COURSES WHERE course_year = ?;", 
				new Object[] {year}, BeanPropertyRowMapper.newInstance(Course.class));
	}

	@Override
	public int update(Course course) {
		return this.jdbcTemplate.update(
		        "INSERT INTO COURSES (course_year) VALUES (?)", course.getYear());
	}

	@Override
	public int delete(int year) {
		return this.jdbcTemplate.update(
		        "DELETE FROM COURSES WHERE course_year = ?", new Object[] {year});
	}

	@Override
	public List<Course> getAll() {
		return this.jdbcTemplate.query("SELECT FROM COURSES", BeanPropertyRowMapper.newInstance(Course.class));
	}
}

