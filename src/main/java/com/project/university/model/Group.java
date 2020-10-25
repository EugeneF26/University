package com.project.university.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
//@Entity
//@Table(name = "groups")
public class Group implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 238402213888528052L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
	private Long id;
	
//	@Column(name = "name")
	private String name;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name ="courseYear")
//	private Course course;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
//	private List<Student> students;
}

