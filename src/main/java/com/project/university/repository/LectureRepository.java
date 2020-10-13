package com.project.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.project.university.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long>, 
JpaSpecificationExecutor<Lecture>{

}
