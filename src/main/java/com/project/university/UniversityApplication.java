package com.project.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.project.university.config.WebConfiguration;
import com.project.university.controller.UniversityController;
import com.project.university.model.Professor;
import com.project.university.model.Student;

/**
 * @author Eugene The class contain methods for managing data of other classes
 */
@Configuration
@ComponentScan
public class UniversityApplication extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	private UniversityController universityController;
	
	@Autowired
	public UniversityApplication(UniversityController universityController) {
		this.universityController = universityController;
	}
	
	public UniversityApplication() {
		
	}
	
	/*
	 * The method start methods of application
	 */
	public void run() {
		
	}

	private void transferStudentToAnotherCourse(Student student) {
	}

	private void fireProfessor(Professor professor) {
	}

	private void acceptNewProfessor(Professor professor) {
	}

	private void acceptNewStudentToCourse(Student student) {
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}

