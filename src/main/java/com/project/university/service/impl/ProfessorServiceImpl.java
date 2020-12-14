package com.project.university.service.impl;

import java.util.List;
import java.util.Optional;

import com.project.university.model.StatusProfessor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project.university.model.Professor;
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
	public Professor acceptNewProfessor(Professor newProfessor) throws Exception {
		newProfessor.setCurrentStatus(StatusProfessor.WORKS);
		return professorRepository.save(newProfessor);
	}

	@Override
	public Professor getProfessor(long id) {
		return professorRepository.getOne(id);
	}

	@Override
	public List<Professor> getProfessors() {
		return professorRepository.findAll();
	}

	@Override
	public void deleteProfessor(long id) {
		professorRepository.deleteById(id);
	}

	@Override
	public void layOfProfessor(Professor professor) {
		Optional<Professor> professorGet = professorRepository.findById(professor.getId());
		professorGet.get().setCurrentStatus(StatusProfessor.FIRED);
		professorRepository.save(professorGet.get());
	}

	@Override
	public void updateProfessor(Professor professor) {
		Optional<Professor> professorGet = professorRepository.findById(professor.getId());
		professorGet.get().setName(professor.getName());
		professorGet.get().setPatronymic(professor.getPatronymic());
		professorRepository.save(professorGet.get());
	}

	@Override
	public void addProfessor(Professor professor) {
		professorRepository.save(professor);
	}
}

