package com.project.university;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.university.repository.CourseRepository;
import com.project.university.repository.GroupRepository;
import com.project.university.repository.StudentRepository;

public class DataPreparer {

	private GroupRepository groupRepository;
	private CourseRepository courseRepository;
	private StudentRepository studentRepository;
	
	@Autowired
	public DataPreparer(GroupRepository groupRepository, CourseRepository courseRepository, 
			StudentRepository studentRepository) {
		this.groupRepository = groupRepository;
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
	}
}

