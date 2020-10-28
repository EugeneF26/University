package com.project.university.service.impl;

import java.util.List;
import java.util.Optional;

import com.project.university.model.StatusStudent;
import com.project.university.model.Student;
import com.project.university.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.model.Course;
import com.project.university.model.Group;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	private GroupRepository groupRepository;

	@Autowired
	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public List<Group> getGroups() {
		return groupRepository.findAll();
	}

	@Override
	public void deleteGroup(long id) {
		groupRepository.deleteById(id);
	}

	@Override
	public Group getGroup(long id) {
		return groupRepository.getOne(id);
	}

	@Override
	public void changeNameGroup(Group group) {
		Optional<Group> groupGet = groupRepository.findById(group.getId());
		groupGet.get().setName(group.getName());
		groupRepository.save(groupGet.get());
	}

	@Override
	public void changeCourseIdGroup(Group group) {
		Optional<Group> groupGet = groupRepository.findById(group.getId());
		groupGet.get().setCourse(Course.builder().id(group.getCourse().getId()).build());
		groupRepository.save(groupGet.get());
	}

	@Override
	public void addNewGroup(Group group) {

	}
}

