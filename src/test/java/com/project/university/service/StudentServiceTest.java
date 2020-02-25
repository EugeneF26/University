package com.project.university.service;

import java.util.List;

import org.dbunit.dataset.NoSuchTableException;
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
import com.project.university.config.DatasourseConfigurationTest;
import com.project.university.entity.Group;
import com.project.university.entity.StatusStudent;
import com.project.university.entity.Student;
import com.project.university.exception.DataAlreadyExistsException;
import com.project.university.repository.StudentRepository;
import com.project.university.service.StudentService;
import com.project.university.service.impl.StudentServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {StudentServiceImpl.class, StudentRepository.class, DatasourseConfigurationTest.class })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/DROP.sql","/CREATE.sql","/INSERT.sql" })
@ActiveProfiles("dev")
public class StudentServiceTest {

    private StudentService studentService;
	
	@Autowired
	public StudentServiceTest(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@Test
	public void testTransferOfStudentToAnotherGroup_WhenTheUserSendsQueryForTransferOfStudentAndTheProgramReturnIdStudentIsThree_thenCorrect() {
		
	}
	
	@Test
	public void testAcceptNewStudentToCourse_() throws NoSuchTableException {
		Student student = Student
				.builder()
				.id(1)
				.name("Michail")
				.surname("Moko")
				.group(Group
						.builder()
						.id(1)
						.build())
				.currentStatus(StatusStudent.ACCEPTED)
				.build();
		MatcherAssert.assertThat(studentService.acceptNewStudent(student).getId(), CoreMatchers.equalTo(4));
	}
	
	@Test
	public void testGetStudents() throws NoSuchTableException {
		List<Student> result = studentService.getStudents();
		MatcherAssert.assertThat(result, IsCollectionWithSize.hasSize(3));
	}
}

