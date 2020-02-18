package com.project.university.entity;

import java.util.List;

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
}

