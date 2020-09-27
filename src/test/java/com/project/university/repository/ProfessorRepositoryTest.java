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
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.config.DatasourseConfigurationTest;
import com.project.university.model.Professor;
import com.project.university.model.StatusProfessor;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.repository.exception.DataSaveException;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {ProfessorRepository.class, DatasourseConfigurationTest.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class ProfessorRepositoryTest {
	
	private CrudRepository<Professor> crudRepository;
	
	@Autowired
	public ProfessorRepositoryTest(CrudRepository<Professor> crudRepository){
		this.crudRepository = crudRepository;
	}
	
	@Test
	public void testSave_WhenTheUserSendsTheProfessorDataAndTheProgramSavesProfessorDataThem_thenCorrect()
			throws DataSetException, FileNotFoundException, SQLException, DataSaveException, DaoLayerException {
		new Professor().getCurrentStatus();
		Professor professor = Professor.builder()
				.name("Alexander")
				.patronymic("Fedorovich")
				.currentStatus(StatusProfessor.WORKS)
				.build();
		MatcherAssert.assertThat(crudRepository.save(professor).getId(), CoreMatchers.equalTo(4));
	}
	
	@Test
	public void testFindProfessorById_WhenTheUserEntersTheIdOfTheProfessorIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException, DaoLayerException {
		Professor professor = crudRepository.findOneById(1);
		MatcherAssert.assertThat(professor.getId(), CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsCountUpdatedRows_thenCorrect()
			throws DataSetException, FileNotFoundException, DataNotFoundException, DaoLayerException {
		new Professor().getCurrentStatus();
		Professor professor = Professor.builder()
				.name("Alexander")
				.patronymic("Fedorovich")
				.id(2)
				.currentStatus(StatusProfessor.WORKS)
				.build();	
		Professor result = crudRepository.update(professor);
		MatcherAssert.assertThat(result, CoreMatchers.equalToObject(professor));
	}
	
	@Test
	public void testGetAll_WhenTheUserSendsQueryForAllDataAndTheProgramReturnThem_thenCorrect()
			throws DataSetException, FileNotFoundException, SQLException, DataNotFoundException, DaoLayerException {
		List<Professor> result = crudRepository.getAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(3));
	}
}

