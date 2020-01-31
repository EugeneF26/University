package com.project.university;

import java.util.Date;

import com.project.university.entity.Course;
import com.project.university.entity.Lecture;
import com.project.university.entity.LectureHall;
import com.project.university.entity.Professor;

/**
 * @author Eugene
 * This is class contain schedule item data
 */
public class ScheduleItem {

	private Date studyDay;
	private Course course;
	private Professor professor;
	private Lecture lecture;
	private LectureHall hall;
	
	public Date getStudyDay() {
		return studyDay;
	}
	
	public void setStudyDay(Date studyDay) {
		this.studyDay = studyDay;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public LectureHall getHall() {
		return hall;
	}

	public void setHall(LectureHall hall) {
		this.hall = hall;
	}
}

