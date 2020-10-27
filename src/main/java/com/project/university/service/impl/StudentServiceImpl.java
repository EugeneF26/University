package com.project.university.service.impl;

import java.util.List;
import java.util.Optional;

import com.project.university.model.Group;
import com.project.university.model.StatusStudent;
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
	
	@Override
	public Student transferStudentToAnotherGroup(Student student, Group group) throws Exception {
		Optional<Student> studentGet = studentRepository.findById(Long.valueOf(student.getId()));
		studentGet.get().setGroup(Group
				.builder()
				.id(group
						.getId())
				.build());
		return studentRepository.save(studentGet.get());
	}

	@Override
	public void expelStudent(Student student) throws Exception {
		Optional<Student> studentGet = studentRepository.findById((long) student.getId());
		studentGet.get().setCurrentStatus(StatusStudent.EXPELLED);
		studentRepository.save(studentGet.get());
	}

	@Override
	public Student acceptNewStudent(Student student) throws Exception {
		student.setCurrentStatus(StatusStudent.STUDY);
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getStudents() throws Exception {
		return studentRepository.findAll();
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public void updateStudent(Student student) {
		studentRepository.getOne(student.getId());

		Optional<Student> studentGet = studentRepository.findById(student.getId());
		studentGet.get().setId(student.getId());
		studentGet.get().setName(student.getName());
		studentGet.get().setSurname(student.getSurname());

		studentRepository.save(studentGet.get());
	}

	@Override
	public Student getStudent(Long id) throws Exception {
		return studentRepository.getOne(id);
	}
}

