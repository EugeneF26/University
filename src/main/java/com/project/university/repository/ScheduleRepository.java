package com.project.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.project.university.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, 
JpaSpecificationExecutor<Schedule>{

}
