package com.project.university.entity;

/** @author Eugene
 * The class contain data of Group
 */
public class Group {
	
	private int groupId;
	private Student student;
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
}

