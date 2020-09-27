package com.project.university.model;

public enum StatusStudent {
	APPLY("APPLY"), EXPELLED("EXPELLED"), STUDY("STUDY");

	private String status;

	StatusStudent(String status) {
		this.status = status;
	}
	
	 public String getStatus() {
         return status;
     }
	 
	 public void setStatus(String status) {
		 this.status = status;
	 }

	@Override
	public String toString() {
		return status;
	}
}

