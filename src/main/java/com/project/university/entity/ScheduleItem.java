package com.project.university.entity;

import java.util.Date;

import lombok.Data;

/** @author Eugene
 * This is class contain schedule item data
 */
@Data
public class ScheduleItem {

	private Date studyDay;
	private Course course;
	private Professor professor;
	private Lecture lecture;
	private LectureHall hall;
}

