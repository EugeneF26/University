package com.project.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.entity.Professor;
import com.project.university.repository.CrudRepository;
import com.project.university.service.impl.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	private CrudRepository<Professor> crudRepository;
	private ProfessorService professorService;
	
	@Autowired
	public ProfessorServiceImpl(CrudRepository<Professor> crudRepository, ProfessorService professorService) {
		this.crudRepository = crudRepository;
		this.professorService = professorService;
	}
	
	public Professor find(int professorId) {
		return crudRepository.find(professorId);
	}
	
	public int save(Professor professor) {
		return crudRepository.save(professor);
	}
	
	public int update(Professor professor) {
		return crudRepository.update(professor);
	}

	public int delete(int professorId) {
		return crudRepository.delete(professorId);
	}

	public List<Professor> getAll() {
		return crudRepository.getAll();
	}

	@Override
	public int truncateProfessorTable() {
		return professorService.truncateProfessorTable();
	}
}

