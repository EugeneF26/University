package com.project.university.entity;

import java.util.List;

public class Student {
	
	private String name;
	private String surname;
	public Student(String name, String surname) {
		this.setName(name);
		this.setSurname(surname);
	}
	
	public List<Student> getStudents(){
		return null;
	}
	
	public void expelStudent(Student student) {
		
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
}

