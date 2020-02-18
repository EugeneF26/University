package com.project.university.service;

import java.util.List;

import com.project.university.entity.Student;

public interface StudentService {
	int transferOfStudentToAnotherGroup(Student student);
	int expelStrudent(Student student);
	int acceptNewStudentToCourse(Student student);
	List<Student> getStudents();
}

