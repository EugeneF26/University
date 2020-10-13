package com.project.university.service;

import java.util.List;

import com.project.university.model.Lecture;

public interface LectureService {
	void removeLecture(Long id);
	void addNewLecture(Lecture lecture);
	void updateLecture(Lecture lecture);
	List<Lecture> getLectures();
}
