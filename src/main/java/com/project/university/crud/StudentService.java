package com.project.university.crud;

import com.project.university.entity.Student;

public interface StudentService {
	int transferOfStudentToAnotherGroup(Student student);
	int truncateStudentsTable();
}

