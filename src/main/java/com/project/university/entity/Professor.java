package com.project.university.entity;

/** @author Eugene
 * The class contain data of Professor
 */
public class Professor {
	
	private int professorId;
	private String name;
	private String surname;
	private String patronymic;
	
	/**
	 * The constructor for filling data
	 * @param professorId of professor int value
	 * @param name of professor string value
	 * @param surname of professor string value
	 * @param patronymic of professor string value
	 */
	public Professor(int professorId, String name, String surname, String patronymic) {
		this.professorId = professorId;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setPofessorId(int id) {
		this.professorId = id;
	}
}

