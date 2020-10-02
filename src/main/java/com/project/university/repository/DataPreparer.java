package com.project.university.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import com.project.university.model.Course;
import com.project.university.model.Group;
import com.project.university.model.Professor;
import com.project.university.model.StatusProfessor;
import com.project.university.model.StatusStudent;
import com.project.university.model.Student;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.repository.exception.DataSaveException;

@Component
public class DataPreparer {

	private GroupRepository groupRepository;
	private CourseRepository courseRepository;
	private StudentRepository studentRepository;
	private ProfessorRepository professorRepository;
	
	private ArrayList<String> first_name = new ArrayList<String>(Arrays.asList(new String[] { "Alexander", "Modest", 
			"Konstantin", "Michail", "Anton", "Vladimir", "Sergey", "Fedor", "Inokentiy", 
			"Vladislav", "Dmitriy", "Igor", "Oleg", "Petr", "Pavel", "Semion", "Arkadiy", 
			"Nicolay", "Silveriy", "Ivan" }));
	
	@Autowired
	public DataPreparer(GroupRepository groupRepository, CourseRepository courseRepository, 
			StudentRepository studentRepository, ProfessorRepository professorRepository) {
		this.groupRepository = groupRepository;
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
		this.professorRepository = professorRepository;
	}
	
	
	 public void prepareData() {
		 
	 }
	 
	
	/*
	 * public void cleanData() { groupRepository.truncateGroups();
	 * courseRepository.truncateCourses();
	 * studentRepository.truncateStudentsAndStudentsCourses(); }
	 */

	public void deleteTables() {
		dropGroupsTable();
		dropCoursesTable(); 
		dropStudentsTable(); 
		dropProfessorsTable();
		/*
		 * dropSchedule_ItemTable(); dropLecture_hallsTable();
		 * dropLecturesTable();
		 */
	}

	public void createTables() {
		createGroupsTable();
		createCoursesTable();
		createStudentsTable(); 
		createProfessorsTable();
		/*
		 * createLecturesTable(); createLecture_hallsTable();
		 * createSchedule_ItemTable();
		 */
	}
	
	public void createStudents() throws DataSaveException, DaoLayerException {
		
		ArrayList<String> last_name = new ArrayList<String>(Arrays.asList(new String[] { "Bakunin", "Kornilov", "Kuchelbecker", "Lomonosov", "Puskin",
				"Korsakov", "Martinov", "Korf", "Esakov", "Delvig", "Volhovskiy", "Dansas", "Pushin", "Maslov",
				"Matushkin", "Tirkov", "Savrasov", "Yakovlev", "Rjevskiy", "Miasoedov" }));
		
		Student student = null;
		
		for(int i = 0; i < 50; i++) {
			student = Student
					.builder()
					.name(first_name
							.stream()
					.skip(new Random().nextInt(first_name.size() - 1))
					.findAny()
					.get())
					.surname(last_name
							.stream()
							.skip(new Random().nextInt(last_name.size() - 1))
							.findAny()
							.get())
					.group(Group
							.builder()
							.id(new Random()
									.nextInt(3) + 1)
							.build())
					.currentStatus(StatusStudent.STUDY)
					.build();
			
			studentRepository.save(student);
		}
		
	}
		
	public void createProfessors() throws DataSaveException, DaoLayerException {
		
		ArrayList<String> patronymic = new ArrayList<String>(Arrays.asList(new String[] { "Bakunin", "Kornilov", "Kuchelbecker", "Lomonosov", "Puskin",
				"Korsakov", "Martinov", "Korf", "Esakov", "Delvig", "Volhovskiy", "Dansas", "Pushin", "Maslov",
				"Matushkin", "Tirkov", "Savrasov", "Yakovlev", "Rjevskiy", "Miasoedov" }));
		
		Professor professor = null;
		
		for(int i = 0; i < 15; i++) {
			professor = Professor
					.builder()
					.name(first_name.stream()
					.skip(new Random().nextInt(first_name.size() - 1))
					.findAny()
					.get())
					.patronymic(first_name
							.stream()
							.skip(new Random().nextInt(patronymic.size() - 1))
							.findAny()
							.get())
					.currentStatus(StatusProfessor.WORKS)
					.build();
			
			professorRepository.save(professor);
	}
}
	
	public void createGroups() throws DataSaveException, DaoLayerException, DataNotFoundException {
		ArrayList<String> groupsName = new ArrayList<String>
		(Arrays.asList(new String[] {"Group A","Group B","Group C",
				"Group D", "Group E", "Group F", "Group G", "Group R"}));
	
		Group group = null;
		
		for(int i = 1; i < 6; i++) {
			group = Group
					.builder()
					.course(Course
							.builder()
							.id(i)
						.build())  //	(int)(Math.random() * ((5 - 1) + 1)
					.name(groupsName
							.stream()
							.skip(new Random()
					.nextInt(groupsName.size() - 1))
							.findAny()
							.get())
					.build();
			
			groupRepository.save(group);
		}
	}
	
	public void createCourses() throws DataSaveException, DaoLayerException, DataNotFoundException {
		
		Course course = null;
		
		for(int i = 1; i < 6; i++) {
			course = Course
					.builder()
					.year(i)
					.build();
			
			courseRepository.save(course);
		}
	}
	
	private void createGroupsTable() {
		try {
			File file = new File(Objects.requireNonNull(DataPreparer.class.getClassLoader().getResource("QueriesDDL/CreateGroupsTable.sql")).getFile());
			groupRepository.createGroupsTable(Files.lines(Paths.get(file.getPath())).collect(Collectors.joining()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createStudentsTable() {
		try {
			File file = new File(Objects.requireNonNull(DataPreparer.class.getClassLoader().getResource("QueriesDDL/CreateStudentsTable.sql")).getFile());
			studentRepository.createStudentsTable(Files.lines(Paths.get(file.getPath())).collect(Collectors.joining()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createCoursesTable() {
		try {
			File file = new File(Objects.requireNonNull(DataPreparer.class.getClassLoader().getResource("QueriesDDL/CreateCoursesTable.sql")).getFile());
			courseRepository.createCoursesTable(Files.lines(Paths.get(file.getPath())).collect(Collectors.joining()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createProfessorsTable() {
		try {
			File file = new File(Objects.requireNonNull(DataPreparer.class.getClassLoader().getResource("QueriesDDL/CreateProfessorsTable.sql")).getFile());
			professorRepository.createProfessorsTable(Files.lines(Paths.get(file.getPath())).collect(Collectors.joining()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * private void createSchedule_ItemTable() {
	 * 
	 * }
	 * 
	 * private void createLecture_hallsTable() { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 * 
	 * private void createLecturesTable() { // TODO Auto-generated method stub
	 * 
	 * }
	 */
	
	/*
	 * private void dropLecturesTable() { try { File file = new
	 * File(Objects.requireNonNull(DataPreparer.class.getClassLoader().
	 * getResource("QueriesDDL/DropLecturesTable.sql")).getFile());
	 * studentRepository.createStudentsTable(Files.lines(Paths.get(file.getPath(
	 * ))).collect(Collectors.joining())); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * private void dropLecture_hallsTable() { try { File file = new
	 * File(Objects.requireNonNull(DataPreparer.class.getClassLoader().
	 * getResource("QueriesDDL/DropLecture_hallsTable.sql")).getFile());
	 * studentRepository.createStudentsTable(Files.lines(Paths.get(file.getPath(
	 * ))).collect(Collectors.joining())); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 */

	/*
	 * private void dropSchedule_ItemTable() { try { File file = new
	 * File(Objects.requireNonNull(DataPreparer.class.getClassLoader().
	 * getResource("QueriesDDL/DropSchedule_ItemTable.sql")).getFile());
	 * scheduleItemRepository.createStudentsTable(Files.lines(Paths.get(file.
	 * getPath())).collect(Collectors.joining())); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 */

	private void dropProfessorsTable() {
		try {
			File file = new File(Objects.requireNonNull(DataPreparer.class.getClassLoader().getResource("QueriesDML/DropProfessorsTable.sql")).getFile());
			professorRepository.createProfessorsTable(Files.lines(Paths.get(file.getPath())).collect(Collectors.joining()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void dropStudentsTable() {
		try {
			File file = new File(Objects.requireNonNull(DataPreparer.class.getClassLoader().getResource("QueriesDML/DropStudentsTable.sql")).getFile());
			studentRepository.createStudentsTable(Files.lines(Paths.get(file.getPath())).collect(Collectors.joining()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void dropCoursesTable() {
		try {
			File file = new File(Objects.requireNonNull(DataPreparer.class.getClassLoader().getResource("QueriesDML/DropCoursesTable.sql")).getFile());
			courseRepository.createCoursesTable(Files.lines(Paths.get(file.getPath())).collect(Collectors.joining()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void dropGroupsTable() {
		try {
			File file = new File(Objects.requireNonNull(DataPreparer.class.getClassLoader().getResource("QueriesDML/DropGroupsTable.sql")).getFile());
			groupRepository.dropGroupsTable(Files.lines(Paths.get(file.getPath())).collect(Collectors.joining()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

