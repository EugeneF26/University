package com.project.university.repository;

import java.io.FileNotFoundException;
import java.sql.SQLException;
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

import com.project.university.config.DatasourseConfigurationTest;
import com.project.university.model.Course;
import com.project.university.model.Group;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.repository.exception.DataSaveException;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {GroupRepository.class, DatasourseConfigurationTest.class})
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
			throws DataSetException, FileNotFoundException, SQLException, DataSaveException, DaoLayerException {
		Group group = Group
				.builder()
				.course(Course
						.builder()
						.id(2)
						.build())
				.name("Y1")
				.build();		
		MatcherAssert.assertThat(crudRepository.save(group).getId(), CoreMatchers.equalTo(5));
	}
	
	@Test
	public void testFindGroupById_WhenTheUserEntersTheIdOfTheGroupIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException, DaoLayerException {
		Group group = crudRepository.findOneById(1);
		MatcherAssert.assertThat(group.getId(), CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsTheSameObject_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException, DaoLayerException {
		Group group = Group
				.builder()
				.id(1)
				.course(Course
						.builder()
						.id(1)
						.build())
				.build();	
		Group result = crudRepository.update(group);
		MatcherAssert.assertThat(result, CoreMatchers.equalToObject(group));
	}
	
	@Test
	public void testGetAll_WhenTheUserSendsQueryForAllDataAndTheProgramReturnThem_thenCorrect()
			throws DataSetException, FileNotFoundException, SQLException, DataNotFoundException, DaoLayerException {
		List<Group> result = crudRepository.getAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(4));
	}
}

