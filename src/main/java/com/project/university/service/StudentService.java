package com.project.university.service;

import java.util.List;

import com.project.university.entity.Student;

public interface StudentService {
	Student transferStudentToAnotherGroup(Student student) throws Exception ;
	Student acceptNewStudent(Student student) throws Exception;
	void expelStrudent(Student student) throws Exception;
	List<Student> getStudents() throws Exception;
}

