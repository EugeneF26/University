package com.project.university.service;

import java.util.List;

import com.project.university.model.Course;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;

public interface CourseService {
	List<Course> getCourses() throws DataNotFoundException, DaoLayerException;
}

