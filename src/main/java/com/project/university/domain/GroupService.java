package com.project.university.domain;

import java.util.ArrayList;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.crud.CrudGroupService;
import com.project.university.crud.CrudRepository;
import com.project.university.entity.Group;

@Service
public class GroupService implements CrudGroupService {
	
	private CrudRepository<Group> crudRepository;
	@SuppressWarnings("unused")
	private CrudGroupService crudGroupService;
	
	@Autowired
	public GroupService(CrudRepository<Group> crudRepository,CrudGroupService crudGroupService) {
		this.crudRepository = crudRepository;
		this.crudGroupService = crudGroupService;
	}
	
	public int save(Group group) {
		return crudRepository.save(group);
	}

	public String find(int groupId) {
		Group result = crudRepository.find(groupId);
		StringJoiner stringJoiner = new StringJoiner("\n");
		stringJoiner.add(String.valueOf("Group_id = " + result.getGroupId() + ";"));
		return stringJoiner.toString();
	}

	public int update(Group group) {
		List<Group> newGroup = new ArrayList<Group>();
		newGroup.add(group);
		return crudRepository.update(group);
	}

	public int delete(int groupId) {
		return crudRepository.delete(groupId);
	}

	public String getAll() {
		List<Group> result = crudRepository.getAll();
		StringJoiner stringJoiner = new StringJoiner("\n");
		for (int i = 0; i < result.size(); i++) {
			stringJoiner.add(String.valueOf("Group_id = " + result.get(i).getGroupId() + ";"));
		}
		return stringJoiner.toString();
	}
}

