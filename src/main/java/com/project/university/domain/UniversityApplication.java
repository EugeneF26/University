package com.project.university.domain;

import com.project.university.entity.Professor;
import com.project.university.entity.Student;

/** @author Eugene
 * The class contain methods for managing data of other classes
 */
public class UniversityApplication {
	
	/*
	 * The method start methods of application 
	 */
	public void run() {
		transferStudentToAnotherCourse(null);
		fireProfessor(null);
		acceptNewProfessor(null);
		acceptNewStudentToCourse(null);
	}
	
	private void transferStudentToAnotherCourse(Student student) {}
	
	private void fireProfessor(Professor professor) {}
	
	private void acceptNewProfessor(Professor professor){}
	
	private void acceptNewStudentToCourse(Student student) {}
}

