package com.project.university.dao;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.DatasourseConfiguration;
import com.project.university.TestDBConfiguration;
import com.project.university.entity.Professor;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {DatasourseConfiguration.class, TestDBConfiguration.class})
@ActiveProfiles("dev")
public class ProfessorRepositoryTest {
	
	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setup() {
		professorRepository.setDataSource(jdbcTemplate);
	}
	
	@Test
	public void testSave_WhenTheUserSendsTheProfessorDataAndTheProgramSavesProfessorDataThem_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Professor professor = Professor.builder()
				.professorName("Alexander")
				.professorSurname("Artemenko")
				.professorPatronymic("Fedorovich")
				.build();
		int rows = professorRepository.save(professor);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testFindProfessorById_WhenTheUserEntersTheIdOfTheProfessorIsOneAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Professor professor = professorRepository.find(1);
		Assert.assertEquals(professor.getProfessorId(), 1);
	}
	
	@Test
	public void testUpdate_WhenUserSendsTheDataInTheMethodAndReturnsCountUpdatedRows_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Professor professor = Professor
				.builder()
				.professorName("Alexander")
				.professorSurname("Artemenko")
				.professorPatronymic("Fedorovich")
				.professorId(2)
				.build();	
		int rows = professorRepository.update(professor);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testDelete_WhenUserSendsTheProfessorIdInTheMethodAndReturnsCountDeletedRows_thenCorrect() 
			throws DataSetException, FileNotFoundException {
		int rows = professorRepository.delete(3);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testGetAll_WhenTheUserSendsQueryForAllDataAndTheProgramReturnThem_thenCorrect()
			throws DataSetException, FileNotFoundException {
		List<Professor> result = professorRepository.getAll();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(3));
	}
}

