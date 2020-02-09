package com.project.university.entity;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NonNull;

/** @author Eugene
 * The class contain data of student and methods working with it
 */
@Data
public class Student {
 
	private int studentId;
	private String name;
	private String surname;
	private List<Student> students;
	private Group group;
	
	/**
	 * The method return list of students by group
	 * @param titleGroup of group string value
	 * @return list of student by group
	 */
	public List<Student> getStudents(int groupId) {
		List<Student> studentsByGroup = students.stream()
				.filter(element -> Integer.valueOf(element.group.getGroupId()).equals(groupId))
				.collect(Collectors.toList());
		return studentsByGroup;
	}
	
	/**
	 * The method delete student from list of students
	 * @param student instance of Student
	 * @see Student#Student(String, String)
	 */
	public void expelStudent(Student student) {
		students
		.removeIf(element -> Integer.valueOf(element.studentId).equals(student.getStudentId()));
	}
}

