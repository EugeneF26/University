package com.project.university.service;

import java.util.List;

import org.dbunit.dataset.NoSuchTableException;

import com.project.university.entity.Student;
import com.project.university.exception.DataNotFoundException;

public interface StudentService {
	Student transferStudentToAnotherGroup(Student student) throws DataNotFoundException ;
	Student acceptNewStudent(Student student) throws NoSuchTableException;
	void expelStrudent(Student student) throws DataNotFoundException;
	List<Student> getStudents() throws NoSuchTableException;
}

