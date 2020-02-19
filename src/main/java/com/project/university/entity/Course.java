package com.project.university.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eugene The class contain data of Course
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
	private Integer id;
	private Integer year;
	private List<Group> groups;
}

