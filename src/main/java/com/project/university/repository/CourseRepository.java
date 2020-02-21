package com.project.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Course;
import com.project.university.entity.Group;

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
	Group group = Group
			.builder()
			.courseId(Course
					.builder()
					.id(course.getId())
					.build())
			.build();
	
		this.jdbcTemplate.update("INSERT INTO COURSES (year, groupId) VALUES (?,?)", course.getYear(), group.getId());
		return this.jdbcTemplate.queryForObject("SELECT id, groupId FROM COURSES WHERE year=?",
				BeanPropertyRowMapper.newInstance(Course.class), course.getYear());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Course findOneById(Integer year) {
		return this.jdbcTemplate.queryForObject("SELECT id, year, groupId FROM COURSES WHERE year = ?;",
				(rs, rowNum) -> {
					return Course.builder().id(rs.getInt("id")).year(rs.getInt("year")).build();
				}, year);
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public Course update(Course course) {
		Group group = Group
				.builder()
				.courseId(Course
						.builder()
						.id(course.getId())
						.build())
				.build();
		this.jdbcTemplate.update("UPDATE COURSES SET groupId=? WHERE year=? ", group.getId(),
				course.getYear());
		return course;
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public void delete(Course course) {
		this.jdbcTemplate.update("DELETE FROM COURSES WHERE id=? AND year = ? AND groupId = ?", course.getId(),
				course.getYear(), course.getId());
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Course> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM COURSES", BeanPropertyRowMapper.newInstance(Course.class));
	}
}

