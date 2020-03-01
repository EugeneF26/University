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
	
	private CrudRepository<Course> crudRepository;

	@Autowired
	public CourseRepositoryTest(CrudRepository<Course> crudRepository){
		this.crudRepository = crudRepository;
	}
	
	@Test
	public void testSave_WhenTheUserSendsTheCourseYearAndTheProgramSavesCourseYearThem_thenCorrect()
			throws DataSetException, FileNotFoundException, SQLException, DataSaveException, DaoLayerException {
		List<Group> group = new ArrayList<>();
		group.add(Group
				.builder()
				.id(1)
				.build());
		
		Course course = Course
				.builder()
				.id(2)
				.groups(group)
				.build();	
		MatcherAssert.assertThat(crudRepository.save(course).getId(), CoreMatchers.equalTo(2));
	}
	
	@Test
	public void testFindCourseByYear_WhenTheUserEntersTheYearOfTheCourseIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException, DaoLayerException {
		Course course = crudRepository.findOneById(1);
		MatcherAssert.assertThat(course.getYear(), CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsTheSameObject_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException, DaoLayerException {
		List<Group> group = new ArrayList<>();
		group.add(Group
				.builder()
				.id(1)
				.build());
		
		Course course = Course
				.builder()
				.year(2)
				.groups(group)
				.build();	
		Course result = crudRepository.update(course);
		MatcherAssert.assertThat(result, CoreMatchers.equalToObject(course));
	}
	
	@Test
	public void testGetAll_WhenTheUserSendsQueryForAllDataAndTheProgramReturnThem_thenCorrect()
			throws DataSetException, FileNotFoundException, SQLException, DataNotFoundException, DaoLayerException {
		List<Course> result = crudRepository.getAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(3));
	}
}

