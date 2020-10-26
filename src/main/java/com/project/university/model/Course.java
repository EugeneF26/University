package com.project.university.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
@Entity
@Table(name = "courses")
public class Course implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1206992403882914691L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "year")
	private Long year;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	private List<Group> groups;
}

