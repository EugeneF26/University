package com.project.university.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.entity.Professor;
import com.project.university.entity.StatusProfessor;
import com.project.university.exception.DataNotFoundException;
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
	public void fireProfessor(Professor professor) throws DataNotFoundException {
		professor.setCurrentStatus(StatusProfessor.ACCEPTED);
		crudRepository.update(professor);
	}

	@Override
	public Professor acceptNewProfessor(Professor professor) throws DataNotFoundException {
		professor.setCurrentStatus(StatusProfessor.FIRED);
		return crudRepository.update(professor);
	}
}

