package com.project.university.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.crud.CrudGroupService;
import com.project.university.crud.CrudRepository;
import com.project.university.entity.Group;

@Service
public class GroupService implements CrudGroupService {
	
	private CrudRepository<Group> crudRepository;
	private CrudGroupService crudGroupService;
	
	@Autowired
	public GroupService(CrudRepository<Group> crudRepository,CrudGroupService crudGroupService) {
		this.crudRepository = crudRepository;
		this.crudGroupService = crudGroupService;
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
		return crudGroupService.truncateGroupTable();
	}
}

