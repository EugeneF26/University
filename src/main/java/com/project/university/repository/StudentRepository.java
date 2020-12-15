package com.project.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.university.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, 
JpaSpecificationExecutor<Student> {

}
