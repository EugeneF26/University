package com.project.university.service;

import java.util.List;

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
import com.project.university.model.Group;
//import com.project.university.repository.GroupRepository;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;

@ExtendWith(SpringExtension.class)
//@SpringJUnitConfig(classes = {GroupService.class, GroupRepository.class, DatasourseConfigurationTest.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class GroupServiceTest {
	
	private GroupService groupService;
	
//	@Autowired
//	public GroupServiceTest(GroupService groupService){
//		this.groupService = groupService;
//	}
	
	/*
	 * @Test public void
	 * testGetGroupsByYear_WhenTheUserSendsQueryForObtainAllGroupByYearCourseAndTheProgramReturnNumberOfList_thenCorrect
	 * () {
	 * 
	 * }
	 */
	
	@Test
	public void testGetAllGroups() throws DataNotFoundException, DaoLayerException {
		List<Group> result = groupService.getGroups();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(5));
	}
}

