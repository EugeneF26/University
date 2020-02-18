package com.project.university.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.entity.Professor;
import com.project.university.repository.CrudRepository;
import com.project.university.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	private CrudRepository<Professor> crudRepository;
	
	@Autowired
	public ProfessorServiceImpl(CrudRepository<Professor> crudRepository) {
		this.crudRepository = crudRepository;
	}

	@Override
	public int fireProfessor(Professor professor) {
		return crudRepository.delete(professor.getProfessorId());
	}

	@Override
	public int acceptNewProfessor(Professor professor) {
		return crudRepository.save(professor);
	}
}

