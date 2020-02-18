package com.project.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.project.university.entity.Student;
import com.project.university.repository.CrudRepository;
import com.project.university.repository.StudentRepository;

/**
 * @author Eugene
 * The class is component of the service layer for Student.
 */
@Service
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository studentRepository;		
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@Override
	public int expelStrudent(int studentId) {
		return studentRepository.delete(studentId);
	}
	
	@Override
	public int transferOfStudentToAnotherGroup(Student student) {
		return studentRepository.update(student);
	}

	@Override
	public int acceptNewStudentToCourse(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.getAll();
	}
}

