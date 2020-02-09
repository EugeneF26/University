package com.project.university;

import java.io.FileNotFoundException;
import org.dbunit.dataset.DataSetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.project.university.dao.StudentRepository;
import com.project.university.entity.Student;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = { TestDBConfiguration.class, StudentRepository.class })
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	@Qualifier("testTemplate")
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setup() {
		studentRepository.setDataSource(jdbcTemplate);
	}

	@Test
	public void testFindStudentsByCourceName_WhenTheUserEntersTheNameOfTheCourseIsMathsAndTheProgramDisplaysTheResult_thenCorrect()
			throws DataSetException, FileNotFoundException {
		Student student = studentRepository.find(1);
		Assert.assertEquals(student.getName(), "Petr");
		Assert.assertEquals(student.getSurname(), "Manshikov");
	}
}

