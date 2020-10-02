package com.project.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.model.Course;
import com.project.university.repository.CrudRepository;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	private CrudRepository<Course> crudRepository;
	
	@Autowired
	public CourseServiceImpl(CrudRepository<Course> crudRepository) {
		this.crudRepository = crudRepository;
	}

	@Override
	public List<Course> getCourses() throws DataNotFoundException, DaoLayerException {
		return crudRepository.getAll();
	}
}

