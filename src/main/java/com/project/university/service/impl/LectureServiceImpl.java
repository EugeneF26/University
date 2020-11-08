package com.project.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.university.model.Lecture;
import com.project.university.repository.LectureRepository;
import com.project.university.service.LectureService;

@Service
public class LectureServiceImpl implements LectureService {
	
	private LectureRepository lectureRepository;
	
	@Autowired
	public LectureServiceImpl(LectureRepository lectureRepository){
		this.lectureRepository = lectureRepository;
	}

	@Override
	public void removeLecture(Long id) {
		lectureRepository.deleteById(id);
	}

	@Override
	public void addNewLecture(Lecture lecture) {
		lectureRepository.save(lecture);
	}

	@Override
	public void updateLecture(Lecture lecture) {
		Lecture result = lectureRepository.getOne(lecture.getId());
		result.setTitle(lecture.getTitle());
		lectureRepository.save(result);
	}

	@Override
	public List<Lecture> getLectures() {
		return lectureRepository.findAll();
	}

	@Override
	public void deleteLecture(long id) {
		lectureRepository.deleteById(id);
	}
}

