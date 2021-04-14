package com.project.university.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Eugene
 * The class contain data of student and methods working with it
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4806386888033637133L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname", nullable = false)
	private String surname;

	@ManyToOne
    @JoinColumn(name ="groupId")
	private Group group;

	@Column(name = "currentStatus")
    @Enumerated(EnumType.STRING)
	private StatusStudent currentStatus;

	@Column(name = "sex", nullable = false)
	private String sex;
}

