package com.project.university.exception;

public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = -2117734685899203829L;
	
	public DataNotFoundException(String message, Throwable cause) {
		 super(message, cause);
	}
	
	public DataNotFoundException(Throwable cause) {
		 super(cause);
	}
	
	public DataNotFoundException(String message) {
		 super(message);
	}
}

