package com.project.university.crud;

import com.project.university.entity.Student;

public interface CrudStudentService {
	
	int transferOfStudentToAnotherGroup(Student student);
	void truncateStudentsTable();

}
