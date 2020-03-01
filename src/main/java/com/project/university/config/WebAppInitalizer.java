package com.project.university.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitalizer extends  AbstractAnnotationConfigDispatcherServletInitializer  {

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

