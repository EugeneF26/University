package com.project.university.repository.exception;

public class DaoLayerException  extends Exception {

	private static final long serialVersionUID = -4123146250024158971L;
	
	public DaoLayerException(String message, Throwable cause) {
		 super(message, cause);
	}
	
	public DaoLayerException(Throwable cause) {
		 super(cause);
	}
	
	public DaoLayerException(String message) {
		 super(message);
	}
	
	public DaoLayerException() {
		 super();
	}
}

