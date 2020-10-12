package com.project.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project.university.model.Professor;
import com.project.university.model.StatusProfessor;
import com.project.university.repository.ProfessorRepository;
import com.project.university.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	private ProfessorRepository professorRepository;

	@Autowired
	public ProfessorServiceImpl(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}

	@Override
	public void fireProfessor(Professor professor) throws Exception {
		professorRepository
		.getOne(professor
				.getId())
		.setCurrentStatus(StatusProfessor.FIRED);
		professorRepository.save(professor);
	}

	@Override
	public Professor acceptNewProfessor(Professor newProfessor) throws Exception {
		Professor professor = Professor
				.builder()
				.name(newProfessor.getName())
				.patronymic(newProfessor.getPatronymic())
				.currentStatus(StatusProfessor.WORKS)
				.build();
		
		return professorRepository.save(professor);
	}

	@Override
	public Professor getProfessor(Professor professor) throws Exception {
		return professorRepository.getOne(professor.getId());
	}

	@Override
	public List<Professor> getProfessors() throws Exception {
		return professorRepository.findAll();
	}
}

