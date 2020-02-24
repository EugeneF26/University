package com.project.university.service;

import java.sql.Date;
import java.time.Period;
import java.util.List;

import com.project.university.entity.ScheduleItem;

public interface ScheduleService {
	void createSchedule(ScheduleItem item);
    ScheduleItem getScheduleByData(Date studyDay);
	List<ScheduleItem> getScheduleByPeriod(Period period);
}

