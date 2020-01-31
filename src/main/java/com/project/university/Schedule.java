package com.project.university;

import java.sql.Date;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.project.university.entity.Group;
import com.project.university.entity.Course;

/** @author Eugene
 * This is class contain a schedule and methods for working with it. 
 */
public class Schedule {

	private List<ScheduleItem> days;
	
	/**
	 * This is method where filling the list of schedule
	 * @param scheduleItem instance SheduleItem class
	 * @see Schedule#days
	 * @see ScheduleItem
	 */
	public void createSchedule(ScheduleItem scheduleItem) {
		days.add(scheduleItem);
	}
	
	/**
	 * This is method return item of schedule by date
	 * @param studyDay of Date value
	 * @return days by day
	 * @see ScheduleItem
	 */
	public ScheduleItem getScheduleByDate(Date studyDay) {
		return days.get(0);
	}
	
	/**
	 * This is method return list of groups
	 * @param year int value of the course number 
	 * @return list with groups
	 * @see Course#getGroups()
	 */
	public List<Group> getGroups(int year) {
		return days.get(0).getCourse().getGroups();
	}
	
	/**
	 * This is method return list items of schedule by period
	 * @param period of Period value
	 * @return list with item of schedule
	 * @see ScheduleItem
	 */
	public List<ScheduleItem> getScheduleByDate(Period period) {
		List<ScheduleItem> scheduleItem = new ArrayList<>(); 
		scheduleItem.add(days.get(0));
		return scheduleItem;
	}
	
	public List<ScheduleItem> getDays() {
		return days;
	}

	public void setDays(List<ScheduleItem> days) {
		this.days = days;
	}
}

