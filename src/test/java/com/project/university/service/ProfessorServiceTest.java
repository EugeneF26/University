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

import com.project.university.config.DatasourseConfiguration;
import com.project.university.entity.Professor;
import com.project.university.repository.ProfessorRepository;
import com.project.university.service.impl.ProfessorServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {ProfessorServiceImpl.class, ProfessorRepository.class, DatasourseConfiguration.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class ProfessorServiceTest {
	
	private ProfessorService professorService;
	
	@Autowired
	public ProfessorServiceTest(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	@Test
	public void testAcceptNewProfessor_WhenTheUserSendsQueryForAddANewProfessorAndTheProgramReturnNumberOfUpdatedRowsIsOne_thenCorrect() {
		int rows = professorService.acceptNewProfessor(Professor
				.builder()
				.professorId(1)
				.professorName("Vladimir")
				.professorSurname("Marko")
				.professorPatronymic("Petrovish")
				.build());
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testFireProfessor_WhenTheUserSendsQueryForDeleteAProfessorAndTheProgramReturnNumberOfUpdatedRowsIsOne_thenCorrect() {
		int rows = professorService.fireProfessor(Professor
				.builder()
				.professorId(1)
				.professorName("Vladimir")
				.professorSurname("Marko")
				.professorPatronymic("Petrovish")
				.build());
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
}

