package com.project.university.service;

import com.project.university.entity.Professor;
import com.project.university.exception.DataNotFoundException;

public interface ProfessorService {
	void fireProfessor(Professor professor) throws DataNotFoundException;
	Professor acceptNewProfessor(Professor professor) throws DataNotFoundException;
}

