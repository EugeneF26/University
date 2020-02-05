package com.project.university.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Group;

@Repository
public class GroupRepository implements CrudRepository<Group> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Group group) {
		return this.jdbcTemplate.update(
		        "INSERT INTO COURSES (group_id) VALUES (?)", new Object[] {group.getGroupId()});
	}

	@Override
	public Group find(int id) {
		return this.jdbcTemplate.queryForObject("SELECT group_id FROM GROUPS WHERE group_id = ?;", 
				new Object[] {id}, BeanPropertyRowMapper.newInstance(Group.class));
	}

	@Override
	public int update(Group group) {
		return this.jdbcTemplate.update(
		        "UPDATE GROUPS SET group_id=? WHERE group_id=? ", new Object[] {group.getGroupId()});
	}

	@Override
	public int delete(int id) {
		return this.jdbcTemplate.update(
		        "DELETE FROM GROUPS WHERE group_id=?", new Object[] {id});
	}

	@Override
	public List<Group> getAll() {
		return this.jdbcTemplate.query("SELECT FROM GROUPS", BeanPropertyRowMapper.newInstance(Group.class));
	}
}

