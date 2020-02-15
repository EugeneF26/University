package com.project.university.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.dao.CrudRepository;
import com.project.university.dao.StudentRepository;
import com.project.university.entity.Student;

/**
 * @author Eugene
 * The class is component of the service layer for Student.
 */
@Service
public class StudentService {
	
	private CrudRepository<Student> crudRepository;
	private StudentRepository studentRepository;
	
	/**
	 * StudentRepostitory initialization
	 * @param studentRepository
	 */
	@Autowired
	public StudentService(CrudRepository<Student> crudRepository, StudentRepository studentRepository) {
		this.crudRepository = crudRepository;
		this.studentRepository = studentRepository;
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
	
	public int transferOfStudentToAnotherGroup(Student student) {
		return studentRepository.regroup(student);
	}
	
	/**
	 * Deletes all students from table
	 */
	public void truncateStudentsTable() {		
		studentRepository.truncate();
	}
}

