package com.project.university.service.impl;

import java.sql.Date;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.entity.ScheduleItem;
import com.project.university.repository.CrudRepository;
import com.project.university.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	private CrudRepository<ScheduleItem> crudRepository;

	@Autowired
	public ScheduleServiceImpl(CrudRepository<ScheduleItem> crudRepository) {
		this.crudRepository = crudRepository;
	}

	@Override
	public void createSchedule(ScheduleItem item) {
		crudRepository.save(item);
	}

	@Override
	public ScheduleItem getScheduleByData(Date studyDay) {
		return null;
	}

	@Override
	public List<ScheduleItem> getScheduleByPeriod(Period period) {
		return null;
	}
}

