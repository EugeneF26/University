package com.project.university.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.dao.StudentRepository;
import com.project.university.entity.Student;

/**
 * @author Eugene
 * The class is component of the service layer for Student.
 */
@Service
public class StudentService {
	
	private StudentRepository studentRepository;
	
	/**
	 * StudentRepostitory initialization
	 * @param studentRepository
	 */
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	/**
	 * Finds student data by id of Student
	 * @param studentId
	 * @return studentResult - result of query for data base
	 */
	public Student find(int studentId) {
		return studentRepository.find(studentId);
	}
	
	/**
	 * Saves student
	 * @param student
	 * @return number of updated rows
	 */
	public int save(Student student) {
		return studentRepository.save(student);
	}
	
	/**
	 * Updates student data
	 * @param student
	 * @return number of updated rows
	 */
	public int update(Student student) {
		return studentRepository.update(student);
	}

	/**
	 * Deletes student by id
	 * @param studentId
	 * @return number of updated rows
	 */
	public int delete(int studentId) {
		return studentRepository.delete(studentId);
	}

	/**
	 * Gets list of all students
	 * @return studentResult
	 */
	public List<Student> getAllStudents() {
		return studentRepository.getAll();
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

