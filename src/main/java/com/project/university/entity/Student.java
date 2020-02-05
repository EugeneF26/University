package com.project.university.entity;

import java.util.ArrayList;
import java.util.List;

/** @author Eugene
 * The class contain data of student and methods working with it
 */
public class Student {

	private String name;
	private String surname;
	private int id;
	private List<Student> students;
	private Group group;

	/**
	 * The constructor for filling data
	 * @param name of student string value
	 * @param surname of student string value
	 */
	public Student(String name, String surname) {
		this.setName(name);
		this.setSurname(surname);
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
			if (students.get(i).getId() == student.getId()) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

