package com.project.university.entity;

import java.util.ArrayList;
import java.util.List;

/** @author Eugene
 * The class contain data of student and methods working with it
 */
public class Student {

	private int studentId;
	private String name;
	private String surname;
	private List<Student> students;
	private Group group;
	
	/**
	 * The constructor for filling data
	 * @param studentId of student int value
	 * @param name of student string value
	 * @param surname of student string value
	 */
	public Student(int studentId, String name, String surname) {
		this.studentId = studentId;
		this.setName(name);
		this.setSurname(surname);
	}
	
	public Student(int studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * The method return list of students by group
	 * @param titleGroup of group string value
	 * @return list of student by group
	 */
	public List<Student> getStudents(int groupId) {
		List<Student> studentsByGroup = new ArrayList<Student>();
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).group.getGroupId() == (groupId)) {
				studentsByGroup.add(students.get(i));
			}
		}
		return studentsByGroup;
	}

	/**
	 * The method delete student from list of students
	 * @param student instance of Student
	 * @see Student#Student(String, String)
	 */
	public void expelStudent(Student student) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getStudentId() == student.getStudentId()) {
				students.remove(i);
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setStudents(List<Student> students) {

	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int id) {
		this.studentId = id;
	}
}

