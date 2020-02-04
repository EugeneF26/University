package com.project.university.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.university.entity.Group;

@Repository
public class GroupRepository implements CrudRepository<Group> {

	@Override
	public int save(Group t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Group find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Group t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Group> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

