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
import com.project.university.crud.CrudCourseService;
import com.project.university.entity.Course;
import com.project.university.entity.Student;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {DatasourseConfiguration.class })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, 
scripts={"/DROP.sql", "/CREATE.sql", "/INSERT.sql"})
@ActiveProfiles("dev")
public class CourseServiceTest {

	private CrudCourseService crudCourseService;

	@Autowired
	public CourseServiceTest(CrudCourseService crudCourseService) {
		this.crudCourseService = crudCourseService;
	}

	@Test
	void testAcceptNewStudentToCourse_WhenTheUserSendsQueryForUpdateDataAndTheProgramReturnNumberOfUpdatedRowsIsOne_thenCorrect() {
		int rows = crudCourseService.acceptNewStudentToCourse(Student.builder().studentId(1).build(),
				Course.builder().courseYear(1).build());
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}

	@Test
	public void testTruncate_WhenTheUserSendsQueryForDeleteAllDataAndTheProgramReturnNumberOfUpdatedRowsIsFour_thenCorrect() {
		int rows = crudCourseService.truncateCoursesTable();
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(4));
	}
}

