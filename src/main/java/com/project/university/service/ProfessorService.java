package com.project.university.service;

import com.project.university.entity.Professor;

public interface ProfessorService {
	void fireProfessor(Professor professor);
	Professor acceptNewProfessor(Professor professor);
}

