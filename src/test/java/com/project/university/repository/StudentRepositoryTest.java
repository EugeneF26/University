package com.project.university.repository;

import java.io.FileNotFoundException;
import java.sql.SQLException;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.config.DatasourseConfigurationTest;
import com.project.university.entity.Group;
import com.project.university.entity.Student;
import com.project.university.exception.DataAlreadyExistsException;
import com.project.university.exception.DataNotFoundException;
import com.project.university.repository.CrudRepository;
import com.project.university.repository.StudentRepository;

import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {StudentRepository.class, DatasourseConfigurationTest.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, 
scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class StudentRepositoryTest {

	private CrudRepository<Student> crudRepository;
	
	@Autowired
	public StudentRepositoryTest(CrudRepository<Student> crudRepository){
		this.crudRepository = crudRepository;
	}
	
	@Test
	public void testSave_WhenTheUserSendsTheStudentDataAndTheProgramSavesAndIncrementStudentIdThem_thenCorrect()
			throws DataSetException, FileNotFoundException, SQLException, DataAlreadyExistsException {	
		Student student = Student
		.builder()
		.name("Pavel")
		.surname("Mrakov")
		.group(Group.builder()
				.id(6)
				.build())
		.build();
		MatcherAssert.assertThat(crudRepository.save(student).getId(), CoreMatchers.equalTo(4));
	}

	@Test
	public void testFindStudentsById_WhenTheUserEntersTheIdOfTheStudentIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException {
		Student student = crudRepository.findOneById(1);
		Assert.assertEquals(student.getName(), "Petr");
		Assert.assertEquals(student.getSurname(), "Manshikov");
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsCountUpdatedRows_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException {
		Student student = Student
		.builder()
		.id(1)
		.name("Petr")
		.surname("Manshikov")
		.group(Group
				.builder()
				.id(1)
				.build())
		.build();
		Student result = crudRepository.update(student);
		MatcherAssert.assertThat(result, CoreMatchers.equalToObject(student));
	}
	
	@Test
	public void testGetAll_WhenTheUserSendsQueryForAllDataAndTheProgramReturnThem_thenCorrect()
			throws DataSetException, FileNotFoundException, SQLException, DataNotFoundException {
		
		List<Student> result = crudRepository.getAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(3));
	}
}

