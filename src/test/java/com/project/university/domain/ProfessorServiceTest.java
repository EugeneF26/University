package com.project.university.domain;

import org.hamcrest.CoreMatchers;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.config.DatasourseConfiguration;
import com.project.university.config.TestDBConfiguration;
import com.project.university.domain.ProfessorService;
import com.project.university.entity.Professor;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {ProfessorService.class, DatasourseConfiguration.class, TestDBConfiguration.class})
@ActiveProfiles("dev")
public class ProfessorServiceTest {
	
	@Autowired
	private ProfessorService professorService;
	
	@Test
	public void testSave_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		Professor NEW_PROFESSOR = Professor
				.builder()
				.professorId(1)
				.professorName("Genadiy")
				.professorSurname("Vasimov")
				.professorPatronymic("Vladimirovich")
				.build();
		int rows = professorService.save(NEW_PROFESSOR);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testFind_WhenTheResultOfQueryDoesNotEmpty_thenCorrect() {
		String result = professorService.find(1);
		MatcherAssert.assertThat(result, IsNull.notNullValue());
	}
	
	@Test
	public void testUpdate_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		Professor NEW_DATA_PROFESSOR = Professor
				.builder()
				.professorId(1)
				.professorName("Genadiy")
				.professorSurname("Vasimov")
				.professorPatronymic("Vladimirovich")
				.build();
		int rows = professorService.update(NEW_DATA_PROFESSOR);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testDelete_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		int rows = professorService.delete(1);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testGetAll_WhenTheResultOfQueryDoesNotEmpty_thenCorrect() {
		String result = professorService.getAll();
		MatcherAssert.assertThat(result,  IsNull.notNullValue());
	}
}

