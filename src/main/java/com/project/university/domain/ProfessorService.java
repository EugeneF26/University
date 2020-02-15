package com.project.university.domain;

import java.util.List;

import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.dao.CrudProfessorService;
import com.project.university.dao.CrudRepository;
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
	
	public String find(int professorId) {
		Professor result = crudRepository.find(professorId);
		StringJoiner stringJoiner = new StringJoiner("\n");
		stringJoiner.add(String.valueOf("Professor_id = " + result.getProfessorId() + ";"));
		stringJoiner.add(String.valueOf("Professor_name = " + result.getProfessorName() + ";"));
		stringJoiner.add(String.valueOf("Professor_surname = " + result.getProfessorSurname() + ";"));
		stringJoiner.add(String.valueOf("Professor_patronymic = " + result.getProfessorPatronymic() + ";"));
		return stringJoiner.toString();
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

	public String getAll() {
		List<Professor> result = crudRepository.getAll();
		StringJoiner stringJoiner = new StringJoiner("\n");
		for (int i = 0; i < result.size(); i++) {
			stringJoiner.add(String.valueOf("Professor_id = " + result.get(i).getProfessorId() + ";"));
			stringJoiner.add(String.valueOf("Professor_name = " + result.get(i).getProfessorName() + ";"));
			stringJoiner.add(String.valueOf("Professor_surname = " + result.get(i).getProfessorSurname() + ";"));
			stringJoiner.add(String.valueOf("Professor_patronymic = " + result.get(i).getProfessorPatronymic() + ";"));
		}
		return stringJoiner.toString();
	}
}

