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
import com.project.university.model.Course;
import com.project.university.repository.CourseRepository;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.service.impl.CourseServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {CourseServiceImpl.class, CourseRepository.class, DatasourseConfigurationTest.class })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/DROP.sql","/CREATE.sql","/INSERT.sql" })
@ActiveProfiles("dev")
public class CourseServiceTest {

	private CourseService courseService;

	@Autowired
	public CourseServiceTest(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@Test
	public void testGetAllCourse() throws DataNotFoundException, DaoLayerException {
		List<Course> result = courseService.getCourses();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(4));
	}
}

