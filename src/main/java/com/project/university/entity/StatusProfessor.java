package com.project.university.entity;

public enum StatusProfessor {
	ACCEPTED("accepted"), FIRED("fired"), WORKS("works");

	private String status;

	StatusProfessor(String status) {
		this.status = status;
	}
	
	 public String getStatus() {
         return status;
     }

	@Override
	public String toString() {
		return status;
	}
}

