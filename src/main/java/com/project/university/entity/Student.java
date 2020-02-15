package com.project.university.entity;

import java.util.List;

import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Eugene
 * The class contain data of student and methods working with it
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
 
	private int studentId;
	private String studentName;
	private String studentSurname;
	private List<Student> students;
	private Group groupId;
	private Course course_year;
	
	/**
	 * The method return list of students by group
	 * @param titleGroup of group string value
	 * @return list of student by group
	 */
	public List<Student> getStudents(int groupId) {
		List<Student> studentsByGroup = students.stream()
				.filter(element -> Integer.valueOf(element.groupId.getGroupId()).equals(groupId))
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

