package com.project.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.model.Course;
import com.project.university.model.Group;
//import com.project.university.repository.GroupRepository;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.service.GroupService;

//@Service
//public class GroupServiceImpl implements GroupService {
//
////	private GroupRepository groupRepository;
//
//	@Autowired
////	public GroupServiceImpl(GroupRepository groupRepository) {
////		this.groupRepository = groupRepository;
////	}
//
////	@Override
////	public List<Group> getGroupsByYear(Course course) {
////		return null;
////	}
////
////	@Override
////	public List<Group> getGroups() throws DataNotFoundException, DaoLayerException {
////		return groupRepository.findAll();
////	}
//}

