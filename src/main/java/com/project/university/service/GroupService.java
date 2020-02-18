package com.project.university.service;

import java.util.List;

import com.project.university.entity.Course;
import com.project.university.entity.Group;

public interface GroupService {
	List<Group> getGroupByYear(Course course);
}

