package com.project.university.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.crud.GroupService;
import com.project.university.crud.CrudRepository;
import com.project.university.entity.Group;

/** @author Eugene
 * The repository class contain methods working with data base
 */
@Repository
public class GroupRepository implements CrudRepository<Group>, GroupService {

	private JdbcTemplate jdbcTemplate;
	
	/** Construct a new JdbcTemplate, given a jdbcTemplate with DataSource to obtain connections from
	 * @param jdbcTemplate - the jdbcTemplate with DataSource to obtain connections from
	 * @see SpringConfig#dataSource()
	 */
	@Autowired
	public void setDataSource(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @see CrudRepository#save(Object)
	 */
	@Override
	public int save(Group group) {
		return this.jdbcTemplate.update(
		        "INSERT INTO GROUPS (group_id) VALUES (?)", group.getGroupId());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Group find(int id) {
		return this.jdbcTemplate.queryForObject("SELECT group_id FROM GROUPS WHERE group_id = ?;", 
				BeanPropertyRowMapper.newInstance(Group.class), id);
	}

	/**
	 * @see CrudRepository#uddate(Object)
	 */
	@Override
	public int update(Group group) {
		return this.jdbcTemplate.update(
		        "UPDATE GROUPS SET group_id=? WHERE group_id=? ", group.getGroupId(), group.getGroupId());
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public int delete(int id) {
		return this.jdbcTemplate.update(
		        "DELETE FROM GROUPS WHERE group_id=?", id);
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Group> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM GROUPS", BeanPropertyRowMapper.newInstance(Group.class));
	}

	@Override
	public int truncateGroupTable() {
		return this.jdbcTemplate.update("DELETE FROM GROUPS");
	}
}

