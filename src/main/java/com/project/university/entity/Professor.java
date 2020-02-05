package com.project.university.entity;

import lombok.Data;

/** @author Eugene
 * The class contain data of Professor
 */
@Data
public class Professor {
	private final int professorId;
	private String name;
	private String surname;
	private String patronymic;
}

