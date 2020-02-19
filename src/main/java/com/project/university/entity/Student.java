package com.project.university.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Eugene
 * The class contain data of student and methods working with it
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	private Integer id;
	private String name;
	private String surname;
	private Group group;
}

