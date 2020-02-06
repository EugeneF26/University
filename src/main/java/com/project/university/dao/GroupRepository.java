package com.project.university.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.entity.Group;

/** @author Eugene
 * The repository class contain methods working with data base
 */
@Repository
public class GroupRepository implements CrudRepository<Group> {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @see CrudRepository#save(Object)
	 */
	@Override
	public int save(Group group) {
		return this.jdbcTemplate.update(
		        "INSERT INTO COURSES (group_id) VALUES (?)", group.getGroupId());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Group find(int id) {
		return new Group(this.jdbcTemplate.queryForObject("SELECT group_id FROM GROUPS WHERE group_id = ?;", 
				Integer.class, id));
	}

	/**
	 * @see CrudRepository#uddate(Object)
	 */
	@Override
	public int update(Group group) {
		return this.jdbcTemplate.update(
		        "UPDATE GROUPS SET group_id=? WHERE group_id=? ", group.getGroupId());
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
		return this.jdbcTemplate.query("SELECT FROM GROUPS", BeanPropertyRowMapper.newInstance(Group.class));
	}
}

