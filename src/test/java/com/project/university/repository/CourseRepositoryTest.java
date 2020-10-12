package com.project.university.repository;

import java.io.FileNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
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
@SpringJUnitConfig(classes = {CourseRepository.class, DatasourseConfigurationTest.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, 
scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class CourseRepositoryTest {
	
	private CourseRepository courseRepository;

	@Autowired
	public CourseRepositoryTest(CourseRepository courseRepository){
		this.courseRepository = courseRepository;
	}
	
	@Test
	public void testSave_WhenTheUserSendsTheCourseYearAndTheProgramSavesCourseYearThem_thenCorrect()
			throws DataSetException, FileNotFoundException, SQLException, DataSaveException, DaoLayerException, DataNotFoundException {
		List<Group> group = new ArrayList<>();
		group.add(Group
				.builder()
				.id(Long.valueOf(5))
				.build());
		
		Course course = Course
				.builder()
				.year(5)
//				.groups(group)
				.build();	
		
		MatcherAssert.assertThat(courseRepository.save(course).getId(), CoreMatchers.equalTo(5));
	}
	
	@Test
	public void testFindCourseByYear_WhenTheUserEntersTheYearOfTheCourseIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException, DaoLayerException {
		Course course = courseRepository.getOne(Long.valueOf(1));
		MatcherAssert.assertThat(course.getYear(), CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsTheSameObject_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException, DaoLayerException {
		List<Group> group = new ArrayList<>();
		group.add(Group
				.builder()
				.id(Long.valueOf(1))
				.build());
		
		Course course = Course
				.builder()
				.year(2)
//				.groups(group)
				.build();	
		Course result = courseRepository.save(course);
		MatcherAssert.assertThat(result, CoreMatchers.equalToObject(course));
	}
	
	@Test
	public void testGetAll_WhenTheUserSendsQueryForAllDataAndTheProgramReturnThem_thenCorrect()
			throws DataSetException, FileNotFoundException, SQLException, DataNotFoundException, DaoLayerException {
		List<Course> result = courseRepository.findAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(4));
	}
}

