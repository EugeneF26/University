package com.project.university.entity;

import java.util.List;

/**
 * @author Eugene The class contain data of Course
 */
public class Course {

	private int year;
	private List<Group> groups;

	public Course(int year) {
		this.year = year;
	}

	public Course(List<Group> groups) {
		this.groups.addAll(groups);
	}
	
	public Course(int year, List<Group> groups) {
		this.year = year;
		this.groups.addAll(groups);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}

