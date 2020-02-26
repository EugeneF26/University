package com.project.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.entity.StatusStudent;
import com.project.university.entity.Student;
import com.project.university.repository.CrudRepository;
import com.project.university.service.StudentService;

/**
 * @author Eugene
 * The class is component of the service layer for Student.
 */
@Service
public class StudentServiceImpl implements StudentService {
	
	private CrudRepository<Student> crudRepository;		
	
	@Autowired
	public StudentServiceImpl(CrudRepository<Student> crudRepository) {
		this.crudRepository = crudRepository;
	}
	
	@Override
	public Student transferStudentToAnotherGroup(Student student) throws Exception {
		return crudRepository.update(student);
	}
	
	@Override
	public void expelStrudent(Student student) throws Exception {
		student.setCurrentStatus(StatusStudent.EXPELLED);
		crudRepository.update(student);
	}

	@Override
	public Student acceptNewStudent(Student student) throws Exception {
		student.setCurrentStatus(StatusStudent.ACCEPTED);
		return crudRepository.save(student);
	}

	@Override
	public List<Student> getStudents() throws Exception {
		return crudRepository.getAll();
	}
}

