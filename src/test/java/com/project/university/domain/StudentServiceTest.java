package com.project.university.domain;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.config.DatasourseConfiguration;
import com.project.university.crud.CrudStudentService;
import com.project.university.domain.StudentService;
import com.project.university.entity.Group;
import com.project.university.entity.Student;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {StudentService.class, DatasourseConfiguration.class})
@ActiveProfiles("dev")
public class StudentServiceTest {
	
	private CrudStudentService crudStudentService;
	
	@Autowired
	public StudentServiceTest(CrudStudentService crudStudentService) {
		this.crudStudentService = crudStudentService;
	}
	
	@Test
	public void testTransferOfStudentToAnotherGroup_WhenTheUserSendsQueryForTransferOfStudentAndTheProgramReturnNumberOfUpdatedRowsIsOne_thenCorrect() {
		int rows = crudStudentService.transferOfStudentToAnotherGroup(Student
				.builder()
				.studentId(4)
				.groupId(Group
						.builder()
						.groupId(1)
						.build())
				.build());
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testTruncate_WhenTheUserSendsQueryForDeleteAllDataAndTheProgramReturnNumberOfUpdatedRowsIsFive_thenCorrect() {
		int rows = crudStudentService.truncateStudentsTable();
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(5));
	}
}

