package com.project.university.entity;

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
	private int groupId;
	private Student student;
}

