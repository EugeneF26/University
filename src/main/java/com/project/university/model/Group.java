package com.project.university.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Eugene
 * The class contain data of Group
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
	private Integer id;
	private String name;
	private Course course;
	private List<Student> students;
}

