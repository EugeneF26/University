package com.project.university.domain;

import java.util.ArrayList;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.crud.CrudCourseService;
import com.project.university.crud.CrudRepository;
import com.project.university.entity.Course;
import com.project.university.entity.Student;

@Service
public class CourseService implements CrudCourseService {

	private CrudRepository<Course> crudRepository;
	private CrudCourseService crudCourseService;
	
	@Autowired
	public CourseService(CrudRepository<Course> crudRepository,CrudCourseService crudCourseService) {
		this.crudRepository = crudRepository;
		this.crudCourseService = crudCourseService;
	}
	
	public int save(Course course) {
		return crudRepository.save(course);
	}
	
	public String find(int courseId) {
		Course result = crudRepository.find(courseId);
		StringJoiner stringJoiner = new StringJoiner("\n");
		stringJoiner.add(String.valueOf("Course_id = " + result.getCourseYear() + ";"));
		return stringJoiner.toString();
	}
	
	public int update(Course course) {
		List<Course> newCourse = new ArrayList<Course>();
		newCourse.add(course);
		return crudRepository.update(course);
	}
	
	public int delete(int courseId) {
		return crudRepository.delete(courseId);
	}
	
	public String getAll() {
		List<Course> result = crudRepository.getAll();
		StringJoiner stringJoiner = new StringJoiner("\n");
		for(int i = 0; i < result.size(); i++) {
		stringJoiner.add(String.valueOf("Course_id = " + result.get(i).getCourseYear() + ";"));
		}
		return stringJoiner.toString();
	}

	@Override
	public int acceptNewStudentToCourse(Student student) {
		// TODO Auto-generated method stub
		return 0;
	}
}

