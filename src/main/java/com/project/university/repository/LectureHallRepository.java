package com.project.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.project.university.model.LectureHall;

public interface LectureHallRepository extends JpaRepository<LectureHall, Long>, 
JpaSpecificationExecutor<LectureHall>{

}
