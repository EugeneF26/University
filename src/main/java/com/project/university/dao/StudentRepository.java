package com.project.university.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.university.entity.Student;

@Repository
public class StudentRepository implements CrudRepository<Student>{

	@Override
	public int save(Student t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Student find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Student t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

