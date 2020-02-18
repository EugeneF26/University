package com.project.university.service;

import com.project.university.entity.Professor;

public interface ProfessorService {
	int fireProfessor(Professor professor);
	int acceptNewProfessor(Professor professor);
}

