package com.project.university.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.entity.Course;
import com.project.university.repository.CrudRepository;
import com.project.university.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@SuppressWarnings("unused")
	private CrudRepository<Course> crudRepository;
	
	@Autowired
	public CourseServiceImpl(CrudRepository<Course> crudRepository) {
		this.crudRepository = crudRepository;
	}

}

