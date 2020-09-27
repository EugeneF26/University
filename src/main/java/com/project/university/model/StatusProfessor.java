package com.project.university.model;

public enum StatusProfessor {
	AT_INTERVIEW("AT_INTERVIEW"), FIRED("FIRED"), WORKS("WORKS");

	private String status;

	StatusProfessor(String status) {
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

