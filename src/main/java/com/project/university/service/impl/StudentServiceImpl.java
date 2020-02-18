package com.project.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public int expelStrudent(Student student) {
		return crudRepository.delete(student.getStudentId());
	}
	
	@Override
	public int transferOfStudentToAnotherGroup(Student student) {
		return 0;
	}

	@Override
	public int acceptNewStudentToCourse(Student student) {
		return crudRepository.save(student);
	}

	@Override
	public List<Student> getStudents() {
		return crudRepository.getAll();
	}
}

