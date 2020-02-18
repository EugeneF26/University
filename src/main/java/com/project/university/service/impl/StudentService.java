package com.project.university.service.impl;

import com.project.university.entity.Student;

public interface StudentService {
	int transferOfStudentToAnotherGroup(Student student);
	int truncateStudentsTable();
}

