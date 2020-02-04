package com.project.university.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.university.entity.Professor;

@Repository
public class ProfessorRepository implements CrudRepository<Professor> {

	@Override
	public int save(Professor t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Professor find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Professor t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Professor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

