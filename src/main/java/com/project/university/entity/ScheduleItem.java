package com.project.university.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/** @author Eugene
 * This is class contain schedule item data
 */
@Data
public class ScheduleItem {

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate studyDay;
	private Course course;
	private Professor professor;
	private Lecture lecture;
	private LectureHall hall;
}

