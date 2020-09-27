package com.project.university.service;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
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
import com.project.university.repository.ProfessorRepository;
import com.project.university.service.impl.ProfessorServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {ProfessorServiceImpl.class, ProfessorRepository.class, DatasourseConfigurationTest.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class ProfessorServiceTest {
	
	private ProfessorService professorService;
	
	@Autowired
	public ProfessorServiceTest(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	@Test
	public void testAcceptNewProfessor_WhenTheUserSendsQueryForAddANewProfessorAndTheProgramReturnIncrementIdOfStudent_thenCorrect() throws Exception {
		Professor professor = Professor
				.builder()
				.id(4)
				.name("Mark")
				.patronymic("Hamatov")
				.currentStatus(StatusProfessor.AT_INTERVIEW)
				.build();
		
		professorService.acceptNewProfessor(professor);
		
		MatcherAssert.assertThat(professorService.getProfessor(professor)
				.getCurrentStatus()
				.getStatus(), 
				CoreMatchers.equalTo("WORKS"));
	}
}

