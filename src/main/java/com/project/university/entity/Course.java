package com.project.university.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author Eugene The class contain data of Course
 */
@Component
@Data
public class Course {
	private int year;
	private List<Group> groups;
}

