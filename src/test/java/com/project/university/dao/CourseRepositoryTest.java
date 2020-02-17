package com.project.university.dao;

import java.io.FileNotFoundException;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.config.DatasourseConfiguration;
import com.project.university.crud.CrudCourseService;
import com.project.university.crud.CrudRepository;
import com.project.university.entity.Course;
import com.project.university.entity.Student;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {DatasourseConfiguration.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, 
scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class CourseRepositoryTest {
	
	private CrudRepository<Course> crudRepository;
	private CrudCourseService crudCourseService;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CourseRepositoryTest(CrudRepository<Course> crudRepository, 
			CrudCourseService crudCourseService, 
			JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
		this.crudRepository = crudRepository;
		this.crudCourseService = crudCourseService;
	}
	
	@Test
	public void testSave_WhenTheUserSendsTheCourseYearAndTheProgramSavesCourseYearThem_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Course course = Course
				.builder()
				.courseYear(5)
				.build();	
		int rows = crudRepository.save(course);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testFindCourseByYear_WhenTheUserEntersTheYearOfTheCourseIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Course course = crudRepository.find(1);
		Assert.assertEquals(course.getCourseYear(), 1);
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsCountUpdatedRows_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Course course = Course
				.builder()
				.courseYear(2)
				.build();	
		int rows = crudRepository.update(course);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testDelete_WhenUserSendsTheCourseYearInTheMethodAndReturnsCountDeletedRows_thenCorrect() 
			throws DataSetException, FileNotFoundException {
		int rows = crudRepository.delete(3);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testGetAll_WhenTheUserSendsQueryForAllDataAndTheProgramReturnThem_thenCorrect()
			throws DataSetException, FileNotFoundException {
		List<Course> result = crudRepository.getAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(4));
	}
	
	@Test
	void testAcceptNewStudentToCourse_WhenTheUserSendsQueryForUpdateDataAndTheProgramReturnNumberOfUpdatedRowsIsOne_thenCorrect() {
		int rows = crudCourseService.acceptNewStudentToCourse(Student
				.builder()
				.studentId(1)
				.build(), Course
				.builder()
				.courseYear(1)
				.build());
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testTruncate_WhenTheUserSendsQueryForDeleteAllDataAndTheProgramReturnNumberOfUpdatedRowsIsFour_thenCorrect() {
		int rows = crudCourseService.truncateCoursesTable();
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(4));
	}
}

