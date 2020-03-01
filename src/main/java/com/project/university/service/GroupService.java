package com.project.university.service;

import java.util.List;

import com.project.university.model.Course;
import com.project.university.model.Group;

public interface GroupService {
	List<Group> getGroupsByYear(Course course);
}

