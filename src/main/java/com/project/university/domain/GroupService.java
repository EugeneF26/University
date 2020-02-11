package com.project.university.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.dao.GroupRepository;
import com.project.university.entity.Group;

@Service
public class GroupService {
	
	@Autowired
	private GroupRepository groupRepository;
	
	public int save(Group group) {
		return groupRepository.save(group);
	}

	public String find(int groupId) {
		Group result = groupRepository.find(groupId);
		StringJoiner stringJoiner = new StringJoiner("\n");
		stringJoiner.add(String.valueOf("Group_id = " + result.getGroupId() + ";"));
		return stringJoiner.toString();
	}

	public int update(Group group) {
		List<Group> newGroup = new ArrayList<Group>();
		newGroup.add(group);
		return groupRepository.update(group);
	}

	public int delete(int groupId) {
		return groupRepository.delete(groupId);
	}

	public String getAll() {
		List<Group> result = groupRepository.getAll();
		StringJoiner stringJoiner = new StringJoiner("\n");
		for (int i = 0; i < result.size(); i++) {
			stringJoiner.add(String.valueOf("Group_id = " + result.get(i).getGroupId() + ";"));
		}
		return stringJoiner.toString();
	}
}

