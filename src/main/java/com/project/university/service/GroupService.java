package com.project.university.service;

import java.util.List;

import com.project.university.model.Course;
import com.project.university.model.Group;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;

public interface GroupService {
	List<Group> getGroupsByYear(Course course);
	List<Group> getGroups() throws DataNotFoundException, DaoLayerException; 
}

