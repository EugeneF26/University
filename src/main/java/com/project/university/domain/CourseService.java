package com.project.university.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.crud.CrudCourseService;
import com.project.university.crud.CrudRepository;
import com.project.university.entity.Course;
import com.project.university.entity.Student;

@Service
public class CourseService implements CrudCourseService, CrudRepository<Course> {

	private CrudRepository<Course> crudRepository;
	private CrudCourseService crudCourseService;
	
	@Autowired
	public CourseService(CrudRepository<Course> crudRepository,CrudCourseService crudCourseService) {
		this.crudRepository = crudRepository;
		this.crudCourseService = crudCourseService;
	}
	
	@Override
	public int save(Course course) {
		return crudRepository.save(course);
	}
	
	@Override
	public Course find(int courseId) {
		return crudRepository.find(courseId);
	}
	
	@Override
	public int update(Course course) {
		return crudRepository.update(course);
	}
	
	@Override
	public int delete(int courseId) {
		return crudRepository.delete(courseId);
	}
	
	@Override
	public List<Course> getAll() {
		return crudRepository.getAll();
	}

	@Override
	public int acceptNewStudentToCourse(Student student, Course course) {
		return crudCourseService.acceptNewStudentToCourse(student, course);
	}

	@Override
	public int truncateCoursesTable() {
		return crudCourseService.truncateCoursesTable();
	}
}

