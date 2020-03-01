package com.project.university.model;

import java.time.LocalDate;
import lombok.Data;

/** @author Eugene
 * This is class contain schedule item data
 */
@Data
public class ScheduleItem {
	private Integer id;
	private LocalDate studyDay;
	private Course course;
	private Professor professor;
	private Lecture lecture;
	private LectureHall hall;
}

