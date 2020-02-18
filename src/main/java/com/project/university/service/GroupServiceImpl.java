package com.project.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.entity.Group;
import com.project.university.repository.CrudRepository;
import com.project.university.service.impl.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	private CrudRepository<Group> crudRepository;
	private GroupService groupService;
	
	@Autowired
	public GroupServiceImpl(CrudRepository<Group> crudRepository, GroupService groupService) {
		this.crudRepository = crudRepository;
		this.groupService = groupService;
	}
	
	public int save(Group group) {
		return crudRepository.save(group);
	}

	public Group find(int groupId) {
		return crudRepository.find(groupId);
	}

	public int update(Group group) {
		return crudRepository.update(group);
	}

	public int delete(int groupId) {
		return crudRepository.delete(groupId);
	}

	public List<Group> getAll() {
		return crudRepository.getAll();
	}

	@Override
	public int truncateGroupTable() {
		return groupService.truncateGroupTable();
	}
}

