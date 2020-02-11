package com.project.university.domain;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.dao.StudentRepository;
import com.project.university.entity.Student;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public String find(int studentId) {
		Student result = studentRepository.find(studentId);
		StringJoiner stringJoiner = new StringJoiner("\n");
		stringJoiner.add(String.valueOf("Student_id = " + result.getStudentId() + ";"));
		stringJoiner.add(String.valueOf("Student_name = " + result.getStudentName() + ";"));
		stringJoiner.add(String.valueOf("Student_surname = " + result.getStudentSurname() + ";"));
		return stringJoiner.toString();
	}
	
	public int save(Student student) {
		return studentRepository.save(student);
	}
	
	public int update(Student student) {
		return studentRepository.update(student);
	}

	public int delete(int studentId) {
		return studentRepository.delete(studentId);
	}

	public String getAllStudents() {
		List<Student> result = studentRepository.getAll();
		StringJoiner stringJoiner = new StringJoiner("\n");
		for (int i = 0; i < result.size(); i++) {
			stringJoiner.add(String.valueOf("Student_id = " + result.get(i).getStudentId() + ";"));
			stringJoiner.add(String.valueOf("Group_id = " + result.get(i).getStudentName() + ";"));
			stringJoiner.add(String.valueOf("Group_description = " + result.get(i).getStudentSurname() + ";"));
		}
		return stringJoiner.toString();
	}
}

