package com.project.university.entity;

import java.util.List;

import lombok.Data;

/** @author Eugene
 * The class contain a schedule and methods for working with it
 */
@Data
public class Schedule {
	private Integer id;
	private List<ScheduleItem> items;
}

