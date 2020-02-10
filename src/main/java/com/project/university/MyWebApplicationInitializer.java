package com.project.university;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

public class MyWebApplicationInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter(
		          "spring.profiles.active", "dev");
	}
}

