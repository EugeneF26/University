package com.project.university;

import java.sql.Date;
import java.time.Period;
import java.util.List;

import com.project.university.entity.Group;

public class Schedule {

	private List<ScheduleItem> days;
	
	public void createSchedule(ScheduleItem scheduleItem) {
		
	}
	
	public ScheduleItem getScheduleByDate(Date studyDay) {
		return null;
	}
	
	public List<Group> getGroups() {
		return null;
	}
	
	public List<ScheduleItem> getScheduleByDate(Period period) {
		return null;
	}

	public List<ScheduleItem> getDays() {
		return days;
	}

	public void setDays(List<ScheduleItem> days) {
		this.days = days;
	}
}

