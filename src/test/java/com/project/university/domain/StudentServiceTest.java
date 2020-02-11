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

import com.project.university.DatasourseConfiguration;
import com.project.university.TestDBConfiguration;
import com.project.university.domain.StudentService;
import com.project.university.entity.Student;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {StudentService.class, DatasourseConfiguration.class, TestDBConfiguration.class})
@ActiveProfiles("dev")
public class StudentServiceTest {
	
	@Autowired
	private StudentService studentService;
	
	@Test
	public void testSave_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		Student NEW_STUDENT = Student.builder()
				.studentId(1)
				.studentName("Nikolay")
				.studentSurname("Pushev")
				.build();
		int rows = studentService.save(NEW_STUDENT);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testFind_WhenTheResultOfQueryDoesNotEmpty_thenCorrect()  {
		String result = studentService.find(1);
		MatcherAssert.assertThat(result, IsNull.notNullValue());
	}
	
	@Test
	public void testUpdate_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		Student NEW_DATA_STUDENT = Student.builder()
				.studentId(1)
				.studentName("Max")
				.studentSurname("Maximov")
				.build();
		int rows = studentService.update(NEW_DATA_STUDENT);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testDelete_WhenTheNumberOfUpdateRowsIsOne_thenCorrect() {
		int rows = studentService.delete(1);
		MatcherAssert.assertThat(rows, CoreMatchers.equalTo(1));
	}
	
	@Test
	public void testGetAll_WhenTheResultOfQueryDoesNotEmpty_thenCorrect()  {
		String result = studentService.getAllStudents();
		MatcherAssert.assertThat(result,  IsNull.notNullValue());
	}
}

