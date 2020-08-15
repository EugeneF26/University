package com.project.university;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.university.model.Student;
import com.project.university.repository.CourseRepository;
import com.project.university.repository.GroupRepository;
import com.project.university.repository.StudentRepository;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataSaveException;

public class DataPreparer {

	private GroupRepository groupRepository;
	private CourseRepository courseRepository;
	private StudentRepository studentRepository;
	
	@Autowired
	public DataPreparer(GroupRepository groupRepository, CourseRepository courseRepository, 
			StudentRepository studentRepository) {
		this.groupRepository = groupRepository;
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
	}
	
	public void createStudents() throws DataSaveException, DaoLayerException {
		
		ArrayList<String> first_name = new ArrayList<String>();
		first_name.addAll(Arrays.asList(new String[] { "Alexander", "Modest", "Konstantin", "Michail", "Anton",
				"Vladimir", "Sergey", "Fedor", "Inokentiy", "Vladislav", "Dmitriy", "Igor", "Oleg", "Petr", "Pavel",
				"Semion", "Arkadiy", "Nicolay", "Silveriy", "Ivan" }));

		ArrayList<String> last_name = new ArrayList<String>();
		last_name.addAll(Arrays.asList(new String[] { "Bakunin", "Kornilov", "Kuchelbecker", "Lomonosov", "Puskin",
				"Korsakov", "Martinov", "Korf", "Esakov", "Delvig", "Volhovskiy", "Dansas", "Pushin", "Maslov",
				"Matushkin", "Tirkov", "Savrasov", "Yakovlev", "Rjevskiy", "Miasoedov" }));

		
		Student student = null;
		IntStream.range(0, 50)
		.forEachOrdered(Student.builder().name(first_name.stream()
				.skip(new Random().nextInt(first_name.size() - 1))
				.findAny()
				.get())
				.surname(first_name.stream()
						.skip(new Random().nextInt(last_name.size() - 1))
						.findAny()
						.get())
				.build());
		
		studentRepository.save(student);
	}
	
	public void createProfessors() {
		
	}
}

