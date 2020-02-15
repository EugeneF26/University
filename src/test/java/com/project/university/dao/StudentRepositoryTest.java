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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.config.DatasourseConfiguration;
import com.project.university.config.TestDBConfiguration;
import com.project.university.dao.StudentRepository;
import com.project.university.entity.Group;
import com.project.university.entity.Student;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {DatasourseConfiguration.class, TestDBConfiguration.class})
@ActiveProfiles("dev")
public class StudentRepositoryTest {

	private StudentRepository studentRepository;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public StudentRepositoryTest(StudentRepository studentRepository, JdbcTemplate jdbcTemplate){
		this.studentRepository = studentRepository;
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Test
	public void testSave_WhenTheUserSendsTheStudentDataAndTheProgramSavesAndIncrementStudentIdThem_thenCorrect()
			throws DataSetException, FileNotFoundException {		
		int rows = studentRepository.save(Student
				.builder()
				.studentName("Pavel")
				.studentSurname("Mrakov")
				.groupId(Group
						.builder()
						.groupId(1)
						.build())
				.build());
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}

	@Test
	public void testFindStudentsById_WhenTheUserEntersTheIdOfTheStudentIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Student student = studentRepository.find(1);
		Assert.assertEquals(student.getStudentName(), "Petr");
		Assert.assertEquals(student.getStudentSurname(), "Manshikov");
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsCountUpdatedRows_thenCorrect()
			throws DataSetException, FileNotFoundException {
		int rows = studentRepository.update(Student
				.builder()
				.studentId(4)
				.studentName("Arkadiy")
				.studentSurname("Morozov")
				.build());
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testDelete_WhenUserSendsTheStudentIdInTheMethodAndReturnsCountDeletedRows_thenCorrect() 
			throws DataSetException, FileNotFoundException {
		int rows = studentRepository.delete(5);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testGetAll_WhenTheUserSendsQueryForAllDataAndTheProgramReturnThem_thenCorrect()
			throws DataSetException, FileNotFoundException {
		List<Student> result = studentRepository.getAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(6));
	}
	
	@Test
	public void testRegroupStudent() {
		int rows = studentRepository.transferOfStudentToAnotherGroup(Student
				.builder()
				.studentId(4)
				.groupId(Group
						.builder()
						.groupId(1)
						.build())
				.build());
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
}

