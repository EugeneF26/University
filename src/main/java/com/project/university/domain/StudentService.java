package com.project.university.domain;

import java.util.ArrayList;
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
		Student resultQuestion = studentRepository.find(studentId);
		Student studentResult = Student
				.builder()
				.studentId(resultQuestion.getStudentId())
				.studentName(resultQuestion.getStudentName())
				.studentSurname(resultQuestion.getStudentSurname())
				.students(resultQuestion.getStudents())
				.groupId(resultQuestion.getGroupId())
				.build();
		return studentResult;
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
		List<Student> resultQuestion = studentRepository.getAll();
		List<Student> studentResult = new ArrayList<>();
		for (int i = 0; i < resultQuestion.size(); i++) {
			studentResult.add(Student
					.builder()
					.studentId(resultQuestion.get(i).getStudentId())
					.studentName(resultQuestion.get(i).getStudentName())
					.studentSurname(resultQuestion.get(i).getStudentSurname())
					.students(resultQuestion.get(i).getStudents())
					.groupId(resultQuestion.get(i).getGroupId())
					.build());
		}
		return studentResult;
	}
}

