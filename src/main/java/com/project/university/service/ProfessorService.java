package com.project.university.service;

import java.util.List;

import com.project.university.model.Professor;
import com.project.university.model.Student;

public interface ProfessorService {
	void fireProfessor(Professor professor) throws Exception;
	Professor acceptNewProfessor(Professor professor) throws Exception;
	Professor getProfessor(Professor professor) throws Exception;
	List<Professor> getProfessors() throws Exception;
}

