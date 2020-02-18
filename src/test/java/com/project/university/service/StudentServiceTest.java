package com.project.university.service;

import java.util.List;

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

import com.project.university.config.DatasourseConfiguration;
import com.project.university.entity.Student;
import com.project.university.repository.StudentRepository;
import com.project.university.service.StudentService;
import com.project.university.service.StudentServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {StudentServiceImpl.class, StudentRepository.class, DatasourseConfiguration.class })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/DROP.sql","/CREATE.sql","/INSERT.sql" })
@ActiveProfiles("dev")
public class StudentServiceTest {

    private StudentService studentService;
	
	@Autowired
	public StudentServiceTest(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@Test
	public void testTransferOfStudentToAnotherGroup_WhenTheUserSendsQueryForTransferOfStudentAndTheProgramReturnNumberOfUpdatedRowsIsOne_thenCorrect() {
		
	}
	
	@Test
	public void testExpelStrudent_() {
		int rows = studentService.expelStrudent(1);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testAcceptNewStudentToCourse_() {
		int rows = studentService.acceptNewStudentToCourse(Student
				.builder()
				.studentId(1)
				.studentName("")
				.studentSurname("")
				.groupId(1)
				.build());
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testGetStudents() {
		List<Student> result = studentService.getStudents();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(5));
	}
}

