package com.project.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.entity.Student;
import com.project.university.repository.CrudRepository;
import com.project.university.service.impl.StudentService;

/**
 * @author Eugene
 * The class is component of the service layer for Student.
 */
@Service
public class StudentServiceImpl implements StudentService {
	
	private CrudRepository<Student> crudRepository;
	private StudentService studentService;
	
	/**
	 * StudentRepostitory initialization
	 * @param studentRepository
	 */
	@Autowired
	public StudentServiceImpl(CrudRepository<Student> crudRepository, StudentService studentService) {
		this.crudRepository = crudRepository;
		this.studentService = studentService;
	}
	
	/**
	 * Finds student data by id of Student
	 * @param studentId
	 * @return studentResult - result of query for data base
	 */
	public Student find(int studentId) {
		return crudRepository.find(studentId);
	}
	
	/**
	 * Saves student
	 * @param student
	 * @return number of updated rows
	 */
	public int save(Student student) {
		return crudRepository.save(student);
	}
	
	/**
	 * Updates student data
	 * @param student
	 * @return number of updated rows
	 */
	public int update(Student student) {
		return crudRepository.update(student);
	}

	/**
	 * Deletes student by id
	 * @param studentId
	 * @return number of updated rows
	 */
	public int delete(int studentId) {
		return crudRepository.delete(studentId);
	}

	/**
	 * Gets list of all students
	 * @return studentResult
	 */
	public List<Student> getAllStudents() {
		return crudRepository.getAll();
	}
	
	@Override
	public int transferOfStudentToAnotherGroup(Student student) {
		return studentService.transferOfStudentToAnotherGroup(student);
	}
	
	/**
	 * Deletes all students from table
	 */
	@Override
	public int truncateStudentsTable() {		
		return studentService.truncateStudentsTable();
	}
}

