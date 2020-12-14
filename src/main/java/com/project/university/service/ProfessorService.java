package com.project.university.service;

import java.util.List;


import com.project.university.model.Professor;

public interface ProfessorService {
	Professor acceptNewProfessor(Professor professor) throws Exception;
	Professor getProfessor(long professor) throws Exception;
	List<Professor> getProfessors() throws Exception;
    void deleteProfessor(long id) throws Exception;
	void layOfProfessor(Professor professor);
	void updateProfessor(Professor selected);
	void addProfessor(Professor professor);
}

