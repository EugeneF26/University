package com.project.university.service;

import java.util.List;
import java.util.Optional;

import com.project.university.model.Course;
import com.project.university.model.Group;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;

public interface GroupService {
	List<Group> getGroups() throws DataNotFoundException, DaoLayerException;
    void deleteGroup(long id);
	Group getGroup(long id);
	void changeNameGroup(Group group);
	void changeCourseIdGroup(Group group);
	void addNewGroup(Group group);
}

