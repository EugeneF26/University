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
	private int professorId;
	private String professorName;
	private String professorSurname;
	private String professorPatronymic;
}

