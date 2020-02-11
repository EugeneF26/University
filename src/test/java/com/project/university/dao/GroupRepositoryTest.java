package com.project.university.dao;

import java.io.FileNotFoundException;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.DatasourseConfiguration;
import com.project.university.TestDBConfiguration;
import com.project.university.entity.Group;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {StudentRepository.class, DatasourseConfiguration.class, TestDBConfiguration.class})
@ActiveProfiles("dev")
public class GroupRepositoryTest {
	
	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setup() {
		groupRepository.setDataSource(jdbcTemplate);
	}
	
	@Test
	public void testSave_WhenTheUserSendsTheGroupDataAndTheProgramSavesCourseIdThem_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Group group = Group
				.builder()
				.groupId(7)
				.build();		
		int rows = groupRepository.save(group);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testFindGroupById_WhenTheUserEntersTheIdOfTheGroupIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Group group = groupRepository.find(1);
		Assert.assertEquals(group.getGroupId(), 1);
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsCountUpdatedRows_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Group group = Group
				.builder()
				.groupId(1)
				.build();	
		int rows = groupRepository.update(group);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testDelete_WhenUserSendsTheGroupIdInTheMethodAndReturnsCountDeletedRows_thenCorrect() 
			throws DataSetException, FileNotFoundException {
		int rows = groupRepository.delete(6);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testGetAll_WhenTheUserSendsQueryForAllDataAndTheProgramReturnThem_thenCorrect()
			throws DataSetException, FileNotFoundException {
		List<Group> result = groupRepository.getAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(7));
	}
}

