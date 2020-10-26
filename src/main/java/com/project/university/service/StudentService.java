package com.project.university.service;

import java.util.List;

import com.project.university.model.Group;
import com.project.university.model.Student;

public interface StudentService {
	
//	Student transferStudentToAnotherGroup(Student student, Group group) throws Exception ;
//
//	Student acceptNewStudent(Student student) throws Exception;
//
//	void expelStudent(Student student) throws Exception;
	
	Student getStudent(Long id) throws Exception;
	
	List<Student> getStudents() throws Exception;
}

