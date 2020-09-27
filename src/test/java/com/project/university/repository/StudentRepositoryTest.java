package com.project.university.repository;

import static org.junit.Assert.assertEquals;

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
import com.project.university.model.Group;
import com.project.university.model.StatusStudent;
import com.project.university.model.Student;
import com.project.university.repository.CrudRepository;
import com.project.university.repository.StudentRepository;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.repository.exception.DataSaveException;

import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

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
			throws DataSetException, FileNotFoundException, SQLException, DataSaveException, DaoLayerException {	
		Student student = Student
		.builder()
		.name("Pavel")
		.surname("Mrakov")
		.group(Group.builder()
				.id(6)
				.build())
		.currentStatus(StatusStudent.STUDY)
		.build();
		MatcherAssert.assertThat(crudRepository.save(student).getId(), CoreMatchers.equalTo(4));
	}

	@Test
	public void testFindStudentsById_WhenTheUserEntersTheIdOfTheStudentIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException, DaoLayerException {
		Student student = crudRepository.findOneById(1);
		assertEquals(student.getName(), "Petr");
		assertEquals(student.getSurname(), "Manshikov");
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsCountUpdatedRows_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException, DaoLayerException {
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
			throws DataSetException, FileNotFoundException, SQLException, DataNotFoundException, DaoLayerException {
		
		List<Student> result = crudRepository.getAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(3));
	}
}

