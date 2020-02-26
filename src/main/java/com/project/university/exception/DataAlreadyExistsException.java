package com.project.university.exception;

public class DataAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 5427697022377634788L;

	public DataAlreadyExistsException(String message, Throwable cause) {
		 super(message, cause);
	}
	
	public DataAlreadyExistsException(Throwable cause) {
		 super(cause);
	}

	public DataAlreadyExistsException(String message) {
		 super(message);
	}
}

