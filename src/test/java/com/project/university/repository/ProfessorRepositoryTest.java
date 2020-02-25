package com.project.university.repository;

import java.io.FileNotFoundException;

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
import com.project.university.entity.Professor;
import com.project.university.entity.StatusProfessor;
import com.project.university.exception.DataAlreadyExistsException;
import com.project.university.exception.DataNotFoundException;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {ProfessorRepository.class, DatasourseConfigurationTest.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class ProfessorRepositoryTest {
	
	@Autowired
	private CrudRepository<Professor> crudRepository;
	
	@Autowired
	public ProfessorRepositoryTest(CrudRepository<Professor> crudRepository){
		this.crudRepository = crudRepository;
	}
	
	@Test
	public void testSave_WhenTheUserSendsTheProfessorDataAndTheProgramSavesProfessorDataThem_thenCorrect()
			throws DataSetException, FileNotFoundException, DataAlreadyExistsException {
		new Professor().getCurrentStatus();
		Professor professor = Professor.builder()
				.name("Alexander")
				.surname("Artemenko")
				.patronymic("Fedorovich")
				.currentStatus(StatusProfessor.ACCEPTED)
				.build();
		MatcherAssert.assertThat(crudRepository.save(professor).getId(), CoreMatchers.equalTo(4));
	}
	
	@Test
	public void testFindProfessorById_WhenTheUserEntersTheIdOfTheProfessorIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException {
		Professor professor = crudRepository.findOneById(1);
		MatcherAssert.assertThat(professor.getId(), CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsCountUpdatedRows_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException {
		new Professor().getCurrentStatus();
		Professor professor = Professor.builder()
				.name("Alexander")
				.surname("Artemenko")
				.patronymic("Fedorovich")
				.id(2)
				.currentStatus(StatusProfessor.ACCEPTED)
				.build();	
		Professor result = crudRepository.update(professor);
		MatcherAssert.assertThat(result, CoreMatchers.equalToObject(professor));
	}
	
	@Test
	public void testGetAll_WhenTheUserSendsQueryForAllDataAndTheProgramReturnThem_thenCorrect()
			throws DataSetException, FileNotFoundException {
		List<Professor> result = crudRepository.getAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(3));
	}
}

