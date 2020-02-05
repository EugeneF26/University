package com.project.university.entity;

import lombok.Data;

/** @author Eugene
 * The class contain data of Group
 */
@Data
public class Group {
	private final int groupId;
	private Student student;
}

