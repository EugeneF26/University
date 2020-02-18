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
	
	/**
	 * StudentRepostitory initialization
	 * @param studentRepository
	 */
	@Autowired
	public StudentServiceImpl(CrudRepository<Student> crudRepository) {
		this.crudRepository = crudRepository;
	}
	
	@Override
	public int expelStrudent(int studentId) {
		return crudRepository.delete(studentId);
	}
	
	@Override
	public int transferOfStudentToAnotherGroup(Student student) {
		return crudRepository.update(student);
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

