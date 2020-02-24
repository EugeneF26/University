package com.project.university.service;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.config.DatasourseConfiguration;
import com.project.university.repository.GroupRepository;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {GroupService.class, GroupRepository.class, DatasourseConfiguration.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, 
scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class GroupServiceTest {
	
	private GroupService groupService;
	
	@Autowired
	public GroupServiceTest(GroupService groupService){
		this.groupService = groupService;
	}
	
	@Test
	public void testGetGroupsByYear_WhenTheUserSendsQueryForObtainAllGroupByYearCourseAndTheProgramReturnNumberOfList_thenCorrect() {

	}
}

