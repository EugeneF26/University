package com.project.university;

import java.io.FileNotFoundException;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.dao.StudentRepository;
import com.project.university.entity.Student;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = { TestDBConfiguration.class, StudentRepository.class })
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	@Qualifier("testTemplate")
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setup() {
		studentRepository.setDataSource(jdbcTemplate);
	}
	
	@Test
	public void testSave_WhenTheUserSendsTheStudentDataAndTheProgramSavesAndIncrementStudentIdThem_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Student student = Student.builder().name("Pavel").surname("Mrakov").build();		
		int rows = studentRepository.save(student);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}

	@Test
	public void testFindStudentsById_WhenTheUserEntersTheIdOfTheStudentIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Student student = studentRepository.find(1);
		Assert.assertEquals(student.getName(), "Petr");
		Assert.assertEquals(student.getSurname(), "Manshikov");
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsCountUpdatedRows_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Student student = Student.builder().studentId(4).name("Arkadiy").surname("Morozov").build();	
		int rows = studentRepository.update(student);
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
}

