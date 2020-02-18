package com.project.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Course;
import com.project.university.entity.Student;
import com.project.university.service.impl.CourseService;
import com.project.university.service.impl.CrudRepository;

/**
 * @author Eugene The repository class contain methods working with data base
 */
@Repository
public class CourseRepository implements CrudRepository<Course>, CourseService {

	private JdbcTemplate jdbcTemplate;

	/** Construct a new JdbcTemplate, given a jdbcTemplate with DataSource to obtain connections from
	 * @param jdbcTemplate - the jdbcTemplate with DataSource to obtain connections from
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
	public int save(Course course) {
		return this.jdbcTemplate.update("INSERT INTO COURSES (course_year) VALUES (?)", course.getCourseYear());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Course find(int year) {
		return this.jdbcTemplate.queryForObject("SELECT course_year FROM COURSES WHERE course_year = ?;",
				BeanPropertyRowMapper.newInstance(Course.class), year);
	}

	/**
	 * @see CrudRepository#update(Object)
	 */
	@Override
	public int update(Course course) {
		return this.jdbcTemplate.update("UPDATE COURSES SET course_year=? WHERE course_year=? ", 
				course.getCourseYear(), course.getCourseYear());
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public int delete(int year) {
		return this.jdbcTemplate.update("DELETE FROM COURSES WHERE course_year = ?", year);
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Course> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM COURSES", BeanPropertyRowMapper.newInstance(Course.class));
	}

	@Override
	public int acceptNewStudentToCourse(Student student, Course course) {
		return this.jdbcTemplate.update("UPDATE STUDENTS_COURSES SET course_year=? WHERE student_id=?", 
				course.getCourseYear(), course.getCourseYear());
	}

	@Override
	public int truncateCoursesTable() {
		return this.jdbcTemplate.update("DELETE FROM COURSES");
	}
}

