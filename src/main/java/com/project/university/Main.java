package com.project.university;

import com.project.university.domain.UniversityApplication;

/** @author Eugene
 * <strong>The main class in application</strong> 
 */
public class Main {
	/**
	 * Here start point of the program
	 * @param args command line values
	 * @see UniversityApplication#run() 
	 */
	public static void main(String[] args) {
		UniversityApplication universityApplication = new UniversityApplication();
		universityApplication.run();
	}
}

