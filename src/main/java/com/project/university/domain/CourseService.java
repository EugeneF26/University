package com.project.university.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.dao.CourseRepository;
import com.project.university.entity.Course;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	public int save(Course course) {
		return courseRepository.save(course);
	}
	
	public String find(int courseId) {
		Course result = courseRepository.find(courseId);
		StringJoiner stringJoiner = new StringJoiner("\n");
		stringJoiner.add(String.valueOf("Course_id = " + result.getCourseYear() + ";"));
		return stringJoiner.toString();
	}
	
	public int update(Course course) {
		List<Course> newCourse = new ArrayList<Course>();
		newCourse.add(course);
		return courseRepository.update(course);
	}
	
	public int delete(int courseId) {
		return courseRepository.delete(courseId);
	}
	
	public String getAll() {
		List<Course> result = courseRepository.getAll();
		StringJoiner stringJoiner = new StringJoiner("\n");
		for(int i = 0; i < result.size(); i++) {
		stringJoiner.add(String.valueOf("Course_id = " + result.get(i).getCourseYear() + ";"));
		}
		return stringJoiner.toString();
	}
}

