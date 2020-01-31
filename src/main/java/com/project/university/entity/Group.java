package com.project.university.entity;

/**
 * @author Eugene
 * The class contain data of Group
 */
public class Group {
	
	private String group;
	private Student student;
	
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
}

