package com.project.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.model.Student;
import com.project.university.repository.StudentRepository;
import com.project.university.service.StudentService;

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
	
//	@Override
//	public Student transferStudentToAnotherGroup(Student student, Group group) throws Exception {
//		Optional<Student> studentGet = studentRepository.findById(Long.valueOf(student.getId()));
//		studentGet.get().setGroup(Group
//				.builder()
//				.id(group
//						.getId())
//				.build());
//		return studentRepository.save(studentGet.get());
//	}
	
//	@Override
//	public void expelStrudent(Student student) throws Exception {
//		Optional<Student> studentGet = studentRepository.findById(Long.valueOf(student.getId()));
//		studentGet.get().setCurrentStatus(StatusStudent.EXPELLED);
//		studentRepository.save(studentGet.get());
//	}

//	@Override
//	public Student acceptNewStudent(Student student) throws Exception {
//		student.setCurrentStatus(StatusStudent.STUDY);
//		return studentRepository.save(student);
//	}

	@Override
	public List<Student> getStudents() throws Exception {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudent(Long id) throws Exception {
		return studentRepository.getOne(id);
	}
}

