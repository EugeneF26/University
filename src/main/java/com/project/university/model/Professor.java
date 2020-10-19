package com.project.university.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eugene The class contain data of Professor
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professors")
public class Professor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6914984380981605846L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "patronymic", nullable = false)
	private String patronymic;
	
	@Column(name = "currentStatus", nullable = false)
    @Enumerated(EnumType.STRING)
	private StatusProfessor currentStatus;
	
	
//	public StatusProfessor getCurrentStatus(){ 
//		return currentStatus; 
//		}

}

