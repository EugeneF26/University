package com.project.university.entity;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Eugene
 * The class contain data of Professor
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
	private Integer id;
	private String name;
	private String surname;
	private String patronymic;
	private StatusProfessor status;
}

