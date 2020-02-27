package com.project.university.repository.exception;

public class DataSaveException extends Exception {
	
	private static final long serialVersionUID = 8145437324740919140L;

	public DataSaveException(String message, Throwable cause) {
		 super(message, cause);
	}
	
	public DataSaveException(Throwable cause) {
		 super(cause);
	}
	
	public DataSaveException(String message) {
		 super(message);
	}
	
	public DataSaveException() {
		 super();
	}
}

