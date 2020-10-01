package com.project.university.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.university.model.Course;
import com.project.university.model.Group;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataSaveException;

/**
 * @author Eugene The repository class contain methods working with data base
 */
@Repository
public class GroupRepository implements CrudRepository<Group> {

	private JdbcTemplate jdbcTemplate;
	private Logger log = LoggerFactory.getLogger(GroupRepository.class.getName());

	/**
	 * Construct a new JdbcTemplate, given a jdbcTemplate with DataSource to obtain
	 * connections from
	 * 
	 * @param jdbcTemplate - the jdbcTemplate with DataSource to obtain connections
	 *                     from
	 * @see SpringConfig#dataSource()
	 */
	
	@Autowired
	public GroupRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @throws DaoLayerException 
	 * @throws DataSaveException 
	 * @see CrudRepository#save(Object)
	 */
	@Override
	public Group save(Group group) throws DaoLayerException, DataSaveException {
		int rows = 0;
		Group result = null;
		log.trace("entry with: {}", group);
		System.out.println(group.getName() + "-" + group.getCourse().getId());
		try {
			rows = this.jdbcTemplate.update("INSERT INTO GROUPS (name, courseId) VALUES (?,?)", group.getName(), group.getCourse().getId());
			result = this.jdbcTemplate.queryForObject("SELECT id FROM GROUPS WHERE name=? AND courseId=?", 
				BeanPropertyRowMapper.newInstance(Group.class), group.getName(), group.getCourse().getId());
		} catch(Exception ex) {
			log.error("Cannot add group to DB", ex);
			throw new DaoLayerException(GroupRepository.class.getName(), ex);
			}
		
		if(rows != 0) {
			log.trace("exit with: {}", group);
			return result;
		} else {
			log.warn("failed to create a new group {} with id {} ", group, group.getId());
			throw new DataSaveException("data was not saved");
		}
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
	
	public void createGroupsTable(String query) {
		this.jdbcTemplate.execute(query);
	}
	
	public void dropGroupsTable(String query) {
		this.jdbcTemplate.update(query);
	}
}

