package com.project.university.service;

import java.util.List;

import com.project.university.entity.Student;

public interface StudentService {
	Student transferStudentToAnotherGroup(Student student);
	Student acceptNewStudent(Student student);
	void expelStrudent(Student student);
	List<Student> getStudents();
}

