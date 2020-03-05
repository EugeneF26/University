package com.project.university.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eugene The class contain data of Course
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	private String name;
	private String surname;
	private Group group;
	private String photo;
	private StatusStudent currentStatus;
}
