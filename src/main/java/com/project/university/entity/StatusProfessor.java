package com.project.university.entity;

public enum StatusProfessor {
	ACCEPTED("accepted"), FIRED("fired");
	private String status; 
	
	StatusProfessor(String status) {
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
}

