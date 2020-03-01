package com.project.university.model;

public enum StatusStudent {
	ACCEPTED("accepted"), EXPELLED("expelled"), STUDY("study");

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

