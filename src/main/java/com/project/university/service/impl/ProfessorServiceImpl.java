package com.project.university.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project.university.model.Professor;
import com.project.university.model.StatusProfessor;
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
	public void fireProfessor(Professor professor) throws Exception {
		professor.setCurrentStatus(StatusProfessor.FIRED);
		crudRepository.update(professor);
	}

	@Override
	public Professor acceptNewProfessor(Professor newProfessor) throws Exception {
		Professor professor = Professor
				.builder()
				.name(newProfessor.getName())
				.patronymic(newProfessor.getPatronymic())
				.currentStatus(StatusProfessor.WORKS)
				.build();
		
		return crudRepository.save(professor);
	}

	@Override
	public Professor getProfessor(Professor professor) throws Exception {
		return crudRepository.findOneById(professor.getId());
	}
}

