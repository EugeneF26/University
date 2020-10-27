package com.project.university.service.impl;

import java.util.List;
import java.util.Optional;

import com.project.university.model.StatusProfessor;
import com.project.university.model.StatusStudent;
import com.project.university.model.Student;
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
		Professor professor = Professor
				.builder()
				.name(newProfessor.getName())
				.patronymic(newProfessor.getPatronymic())
//				.currentStatus(StatusProfessor.WORKS)
				.build();
		
		return professorRepository.save(professor);
	}

	@Override
	public Professor getProfessor(long id) throws Exception {
		return professorRepository.getOne(id);
	}

	@Override
	public List<Professor> getProfessors() throws Exception {
		return professorRepository.findAll();
	}

	@Override
	public void deleteProfessor(long id) throws Exception {
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
}

