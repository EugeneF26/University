package com.project.university.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import lombok.Data;

/** @author Eugene
 * The class contain a schedule and methods for working with it
 */
@Data
public class Schedule {

	private List<ScheduleItem> days;
	
	/**
	 * The method where filling the list of schedule
	 * @param scheduleItem instance SheduleItem class
	 * @see Schedule#days
	 * @see ScheduleItem
	 */
	public void createSchedule(ScheduleItem scheduleItem) {
		days.add(scheduleItem);
	}
	
	/**
	 * The method return item of schedule by date
	 * @param date of Date value
	 * @return days by day
	 * @see ScheduleItem
	 */
	public ScheduleItem getScheduleByDate(Date studyDay) {		
		return days
				.stream()
				.filter(element -> element
						.getStudyDay()
						.equals(studyDay))
				.findFirst()
				.get();
	}
	
	/**
	 * The method return list of groups
	 * @param year int value of the course number 
	 * @return list with groups
	 * @see Course#getGroups()
	 */
	public List<Group> getGroups(int year) {
		List<Group> groups = new ArrayList<>();
		for(int i = 0; i < days.size(); i++) {
			if(Integer.valueOf(days.get(i).getCourse().getCourseYear()).equals(year)) {
				for(int i2 = 0; i2 < days.get(i).getCourse().getGroups().size(); i2++) {
					groups.add(days.get(i).getCourse().getGroups().get(i2));
				}
			}
		}			
		return groups;
	}
	
	/**
	 * The method return list items of schedule by period
	 * @param period of Period value
	 * @return list with item of schedule
	 * @see ScheduleItem
	 */
	public List<ScheduleItem> getScheduleByDate(Period period) {
		LocalDate localDate = LocalDate.of(period.getYears(), period.getMonths(), period.getDays()); 
		List<ScheduleItem> scheduleItem = days
				.stream()
				.filter(element -> element.getStudyDay().equals(localDate))
				.collect(Collectors.toList());
		return scheduleItem;
	}
}

