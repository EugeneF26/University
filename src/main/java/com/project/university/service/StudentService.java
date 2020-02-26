package com.project.university.service;

import java.sql.SQLException;
import java.util.List;

import com.project.university.entity.Student;
import com.project.university.exception.DataNotFoundException;

public interface StudentService {
	Student transferStudentToAnotherGroup(Student student) throws DataNotFoundException ;
	Student acceptNewStudent(Student student) throws SQLException;
	void expelStrudent(Student student) throws DataNotFoundException;
	List<Student> getStudents() throws SQLException;
}

