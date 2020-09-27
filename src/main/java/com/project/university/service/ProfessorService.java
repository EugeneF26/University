package com.project.university.service;

import com.project.university.model.Professor;

public interface ProfessorService {
	void fireProfessor(Professor professor) throws Exception;
	Professor acceptNewProfessor(Professor professor) throws Exception;
	Professor getProfessor(Professor professor) throws Exception;
}

