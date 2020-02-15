package com.project.university.dao;

import com.project.university.entity.Student;

public interface CrudStudentService {
	
	int transferOfStudentToAnotherGroup(Student student);
	void truncateStudentsTable();

}
