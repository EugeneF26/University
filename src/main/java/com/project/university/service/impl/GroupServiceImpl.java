package com.project.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.entity.Course;
import com.project.university.entity.Group;
import com.project.university.repository.CrudRepository;
import com.project.university.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	private CrudRepository<Course> crudRepository;
	
	@Autowired
	public GroupServiceImpl(CrudRepository<Course> crudRepository) {
		this.crudRepository = crudRepository;
	}

	@Override
	public List<Group> getGroupByYear(Course course) {
		return null;
	}
}

