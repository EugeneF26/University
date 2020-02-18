package com.project.university.domain;

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
import com.project.university.crud.ProfessorService;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {DatasourseConfiguration.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class ProfessorServiceTest {
	
	private ProfessorService professorService;
	
	@Autowired
	public ProfessorServiceTest(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	@Test
	public void testTruncate_WhenTheUserSendsQueryForDeleteAllDataAndTheProgramReturnNumberOfUpdatedRowsIsFour_thenCorrect() {
		int rows = professorService.truncateProfessorTable();
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(3));
	}
}

