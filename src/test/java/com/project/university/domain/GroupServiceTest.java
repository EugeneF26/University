package com.project.university.domain;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.DatasourseConfiguration;
import com.project.university.TestDBConfiguration;
import com.project.university.domain.GroupService;
import com.project.university.entity.Group;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {GroupService.class, DatasourseConfiguration.class, TestDBConfiguration.class})
@ActiveProfiles("dev")
public class GroupServiceTest {
	
	@Autowired
	GroupService groupService;
	
	@Test
	public void testSave_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		Group NEW_GROUP = Group
				.builder()
				.groupId(1)
				.build();
		int rows = groupService.save(NEW_GROUP);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testFind_WhenTheResultOfQueryDoesNotEmpty_thenCorrect() {
		String result = groupService.find(1);
		MatcherAssert.assertThat(result, IsNull.notNullValue());
	}
	
	@Test
	public void testUpdate_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		Group NEW_DATA_GROUP = Group
				.builder()
				.groupId(1)
				.build();
		int rows = groupService.update(NEW_DATA_GROUP);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testDelete_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		int rows = groupService.delete(1);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testGetAll_WhenTheResultOfQueryDoesNotEmpty_thenCorrect() {
		String result = groupService.getAll();
		MatcherAssert.assertThat(result,  IsNull.notNullValue());
	}
}

