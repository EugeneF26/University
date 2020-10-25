package com.project.university.repository;

import java.util.List;

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

import com.project.university.config.DatasourseConfigurationTest;
import com.project.university.model.Course;
import com.project.university.model.Group;
import com.project.university.model.Professor;
import com.project.university.model.Student;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.repository.exception.DataSaveException;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {DataPreparer.class, DatasourseConfigurationTest.class})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts={"/DROP.sql", "/CREATE.sql"})
@ActiveProfiles("dev")
public class DataPreparerTest {
	
	private DataPreparer dataPreparer;
//	private GroupRepository groupRepository;
	private CourseRepository courseRepository;
	private ProfessorRepository professorRepository;
	private StudentRepository studentRepository;
	
//	@Autowired
//	public DataPreparerTest(DataPreparer dataPreparer, GroupRepository groupRepository,
//			CourseRepository courseRepository, ProfessorRepository professorRepository) {
//		this.dataPreparer = dataPreparer;
//		this.groupRepository = groupRepository;
//		this.courseRepository = courseRepository;
//		this.professorRepository = professorRepository;
//	}
	
	@Test
	public void testCreateStudents() throws DataSaveException, DaoLayerException, DataNotFoundException {
		dataPreparer.createStudents();
		List<Student> students = studentRepository.findAll();
		MatcherAssert.assertThat(students, IsCollectionWithSize.hasSize(50));
	}
	
	@Test
	public void testCreateProfessors() throws DataSaveException, DaoLayerException, DataNotFoundException {
//		dataPreparer.createProfessors();
		List<Professor> professors = professorRepository.findAll();
		MatcherAssert.assertThat(professors, IsCollectionWithSize.hasSize(15));
	}
	
//	@Test
////	public void createGroups() throws DataSaveException, DaoLayerException, DataNotFoundException {
////		dataPreparer.createCourses();
////		dataPreparer.createGroups();
//		List<Group> groups = groupRepository.findAll();
//		MatcherAssert.assertThat(groups, IsCollectionWithSize.hasSize(5));
//	}
	
	@Test
	public void createCourses() throws DataSaveException, DaoLayerException, DataNotFoundException {
//		dataPreparer.createCourses();
		List<Course> course = courseRepository.findAll();
		MatcherAssert.assertThat(course, IsCollectionWithSize.hasSize(5));
	}
}

