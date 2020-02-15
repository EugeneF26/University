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

import com.project.university.config.DatasourseConfiguration;
import com.project.university.config.TestDBConfiguration;
import com.project.university.domain.CourseService;
import com.project.university.entity.Course;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {CourseService.class, DatasourseConfiguration.class, TestDBConfiguration.class})
@ActiveProfiles("dev")
public class CourseServiceTest {
	
	@Autowired
	CourseService courseService;
	
	@Test
	public void testSave_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		Course NEW_GROUP = Course
				.builder()
				.courseYear(1)
				.build();
		int rows = courseService.save(NEW_GROUP);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testFind_WhenTheResultOfQueryDoesNotEmpty_thenCorrect() {
		String result = courseService.find(1);
		MatcherAssert.assertThat(result, IsNull.notNullValue());
	}
	
	@Test
	public void testUpdate_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		Course NEW_DATA_GROUP = Course
				.builder()
				.courseYear(1)
				.build();
		int rows = courseService.update(NEW_DATA_GROUP);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testDelete_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		int rows = courseService.delete(1);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testGetAll_WhenTheResultOfQueryDoesNotEmpty_thenCorrect() {
		String result = courseService.getAll();
		MatcherAssert.assertThat(result,  IsNull.notNullValue());
	}
}

