package com.project.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.model.Course;
import com.project.university.model.Group;

/**
 * @author Eugene The repository class contain methods working with data base
 */
@Repository
public class GroupRepository implements CrudRepository<Group> {

	private JdbcTemplate jdbcTemplate;

	/**
	 * Construct a new JdbcTemplate, given a jdbcTemplate with DataSource to obtain
	 * connections from
	 * 
	 * @param jdbcTemplate - the jdbcTemplate with DataSource to obtain connections
	 *                     from
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
	public Group save(Group group) {	
		this.jdbcTemplate.update("INSERT INTO GROUPS (courseId, name) VALUES (?,?)", group.getCourse().getId(), group.getName());
		return this.jdbcTemplate.queryForObject("SELECT id FROM GROUPS WHERE courseId=? AND name=?", 
				BeanPropertyRowMapper.newInstance(Group.class) , group.getCourse().getId(), group.getName());
	}

	/**
	 * @see CrudRepository#find(int)
	 */
	@Override
	public Group findOneById(Integer id) {
		return this.jdbcTemplate.queryForObject("SELECT id, courseId FROM GROUPS WHERE id = ?;",
				(rs, rowNum) -> {
					return Group
							.builder()
							.id(rs.getInt("id"))
							.course(Course
									.builder()
									.id(rs.getInt("id"))
									.build())
							.build();
				}, id);
	}

	/**
	 * @see CrudRepository#uddate(Object)
	 */
	@Override
	public Group update(Group group) {	
		this.jdbcTemplate.update("UPDATE GROUPS SET courseId=? WHERE courseId=?", group.getCourse().getId(), group.getCourse().getId());
		return group;
	}

	/**
	 * @see CrudRepository#delete(int)
	 */
	@Override
	public void delete(Group group) {
		this.jdbcTemplate.update("DELETE FROM GROUPS WHERE id=?", group.getId());
	}

	/**
	 * @see CrudRepository#getAll()
	 */
	@Override
	public List<Group> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM GROUPS", (rs, rowNum) -> {
			return Group
					.builder()
					.id(rs.getInt("id"))
					.course(Course.builder()
							.id(rs.getInt("courseId"))
							.build())
					.build();
		});
	}
}

