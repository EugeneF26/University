package com.project.university.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** @author Eugene
 * The class contain data of Lecture hall
 */
@Data
@EqualsAndHashCode
public class LectureHall {
	private String floor;
	private int number;
}

