package com.project.university.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.crud.CrudProfessorService;
import com.project.university.crud.CrudRepository;
import com.project.university.entity.Professor;

@Service
public class ProfessorService implements CrudProfessorService {

	private CrudRepository<Professor> crudRepository;
	private CrudProfessorService crudProfessorService;
	
	@Autowired
	public ProfessorService(CrudRepository<Professor> crudRepository, CrudProfessorService crudProfessorService) {
		this.crudRepository = crudRepository;
		this.crudProfessorService = crudProfessorService;
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
		return crudProfessorService.truncateProfessorTable();
	}
}

