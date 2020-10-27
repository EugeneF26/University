package com.project.university.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Eugene
 * The class contain data of Lecture hall
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lecture_Halls")
public class LectureHall implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 336122319284431002L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "floor")
	private Long floor;
	
	@Column(name = "classroom")
	private Long classroom;
}

