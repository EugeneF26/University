package com.project.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.project.university.model.Professor;

/**
 * @author Eugene The repository class contain methods working with data base
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>, 
JpaSpecificationExecutor<Professor> {

}

