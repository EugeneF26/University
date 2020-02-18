package com.project.university.repository;

import java.io.FileNotFoundException;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.config.DatasourseConfiguration;
import com.project.university.entity.Group;
import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {GroupRepository.class, DatasourseConfiguration.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class GroupRepositoryTest {
	
	private CrudRepository<Group> crudRepository;
	
	@Autowired
	public GroupRepositoryTest(CrudRepository<Group> groupRepository){
		this.crudRepository = groupRepository;
	}
	
	@Test
	public void testSave_WhenTheUserSendsTheGroupDataAndTheProgramSavesCourseIdThem_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Group group = Group
				.builder()
				.groupId(7)
				.build();		
		int rows = crudRepository.save(group);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testFindGroupById_WhenTheUserEntersTheIdOfTheGroupIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Group group = crudRepository.find(1);
		Assert.assertEquals(group.getGroupId(), 1);
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsCountUpdatedRows_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Group group = Group
				.builder()
				.groupId(1)
				.build();	
		int rows = crudRepository.update(group);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testDelete_WhenUserSendsTheGroupIdInTheMethodAndReturnsCountDeletedRows_thenCorrect() 
			throws DataSetException, FileNotFoundException {
		int rows = crudRepository.delete(5);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testGetAll_WhenTheUserSendsQueryForAllDataAndTheProgramReturnThem_thenCorrect()
			throws DataSetException, FileNotFoundException {
		List<Group> result = crudRepository.getAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(5));
	}
}

