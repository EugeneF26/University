package com.project.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.entity.Course;
import com.project.university.entity.Student;
import com.project.university.repository.CrudRepository;
import com.project.university.service.impl.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	private CrudRepository<Course> crudRepository;
	private CourseService courseService;
	
	@Autowired
	public CourseServiceImpl(CrudRepository<Course> crudRepository,CourseService courseService) {
		this.crudRepository = crudRepository;
		this.courseService = courseService;
	}
	
	public int save(Course course) {
		return crudRepository.save(course);
	}
	
	public Course find(int courseId) {
		return crudRepository.find(courseId);
	}
	
	public int update(Course course) {
		return crudRepository.update(course);
	}
	
	public int delete(int courseId) {
		return crudRepository.delete(courseId);
	}
	
	public List<Course> getAll() {
		return crudRepository.getAll();
	}

	@Override
	public int acceptNewStudentToCourse(Student student, Course course) {
		return courseService.acceptNewStudentToCourse(student, course);
	}

	@Override
	public int truncateCoursesTable() {
		return courseService.truncateCoursesTable();
	}
}

